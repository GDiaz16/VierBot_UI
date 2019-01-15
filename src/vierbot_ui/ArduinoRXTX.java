package vierbot_ui;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * @author Gonzalo Diaz
 */
public class ArduinoRXTX {

    //Se crea un obteto llamado Arduino para instanciar la clase Arduino
    //De la librería <del>Arduino para Java</del> PanamaHitek_Arduino
    private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    //Se instancia un objeto de la clase MultiMessage
    //Se indica que se van a leer 3 sensores y que la clase Arduino fue instanciada
    //mediante el obteto Arduino
    private PanamaHitek_MultiMessage multi = new PanamaHitek_MultiMessage(3, arduino);

    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (arduino.isMessageAvailable()) {
                    String message = arduino.printMessage();
                    if (message.equals("%")) {
                        ok = true;
                        protocol();
                    }
                    System.out.println(message);

                }
            } catch (ArduinoException | SerialPortException spex) {
            }

        }
    };

    public ArduinoRXTX() {
        try {
            arduino.arduinoRXTX("COM4", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(ArduinoRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(int servo, int angulo) {
        try {
            arduino.sendData("&;");
            arduino.sendData(servo + ";");
            arduino.sendData(angulo + ";");
            arduino.sendData("$;");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ArduinoRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean ok = true;
    LinkedList buffer = new LinkedList();

    public void buffer(int servo, int angulo) {
        int par[] = {servo, angulo};
        buffer.add(par);

    }

    public void protocol() {
        if (!buffer.isEmpty()) {
            sendData(((int[]) buffer.poll())[0], ((int[]) buffer.poll())[1]);
            ok = false;
        }
    }
}
