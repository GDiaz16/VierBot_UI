package vierbot_ui;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import sun.applet.Main;

/**
 * @author Gonzalo Diaz
 */

public class VierBot_UI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame frame = new JFrame("VierBot");
        frame.setBackground(Color.decode("#58D3F7"));
        frame.add(new VierBotDriver());
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }

}
