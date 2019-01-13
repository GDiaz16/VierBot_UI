package vierbot_ui;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import sun.applet.Main;

/**
 * @author Gonzalo Diaz
 */
public class VierBot_UI implements Runnable {

    /**
     * @param args the command line arguments
     */
    VierBotDriver vDriver;

    public VierBot_UI(VierBotDriver vDriver) {
        this.vDriver = vDriver;
    }

    public static void main(String[] args) {

        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scheduler scheduler = new Scheduler();
        VierBotDriver vDriver = new VierBotDriver(scheduler);
        VierBot_UI ui = new VierBot_UI(vDriver);

        JFrame frame = new JFrame("VierBot");
        frame.setBackground(Color.decode("#58D3F7"));
        frame.add(vDriver);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        Thread hilo = new Thread(ui);
        scheduler.start();
        hilo.start();
        //ui.run();
    }

    @Override
    public void run() {
        while (true) {
           // System.out.println("hilo 1");
            vDriver.run();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            // this.wait(100);
        }
    }

}
