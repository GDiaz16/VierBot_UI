package vierbot_ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gonzalo Diaz
 */
public class Scheduler {

    int xLateral = 150, y = 200, xFrontal = xLateral + 250, pX = 200, pY = 200;
    //valores geometricos
    int L2 = 70, L1 = 70;

    public void guardarCoordenadas(){
        
    }
    public void write(String datos) {
        File archivo;
        try {
            String ruta = "src/archivos/archivo.txt";
            archivo = new File(ruta);
            FileWriter w = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(datos);
           // wr.append(" jeje");
            wr.close();
            bw.close();
        } catch (IOException ex) {
            //Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getL2() {
        return L2;
    }

    public int getL1() {
        return L1;
    }

    public double[] toCartesian(double r, double angle) {
        double point[] = new double[2];
        point[0] = r * Math.cos(angle * Math.PI / 180);
        point[1] = r * Math.sin(angle * Math.PI / 180);
        return point;
    }

//cinematica para un brazo con dos grados de libertad
    public double[] kinematic(double px, double py) {
        double cosq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(L1, 2) - Math.pow(L2, 2)) / (2 * L2 * L1);
        double sinq2 = Math.sqrt(1 - Math.pow(cosq2, 2));
        double q2 = Math.atan2(sinq2, cosq2);
        double q1 = Math.atan2(py, px) - Math.atan2(L2 * sinq2, L1 + L2 * cosq2);
        double angulos[] = {q1 * (180 / Math.PI), q2 * (180 / Math.PI)};
        return angulos;
    }

    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
