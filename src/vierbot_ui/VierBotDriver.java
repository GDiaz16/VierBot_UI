/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vierbot_ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Gonzalo Diaz
 */
public class VierBotDriver extends javax.swing.JPanel {

    int x = 200, y = 200, x1 = 10, y1 = 30, p1 = 200, p2 = 250;
    int LBrazo = 40, LAnteBrazo = 60;
    ArduinoRXTX puerto;
        
    public double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double[] toCartesian(double r, double angle) {
        double point[] = new double[2];
        point[0] = r * Math.cos(angle);
        point[1] = r * Math.sin(angle);

        return point;
    }
//cinematica para un brazo con dos grados de libertad

    public int[] kinematic(int finalX, int finalY, int LBrazo, int LAntebrazo) {
        double hipotenusa = Math.sqrt(Math.pow(finalX, 2) + Math.pow(finalY, 2));
        double alfa = -Math.atan2(finalY, finalX);
        double beta = -Math.acos((Math.pow(LBrazo, 2) - Math.pow(LAntebrazo, 2) + Math.pow(hipotenusa, 2)) / (2 * LBrazo * hipotenusa));
        int angBrazo = (int) -(alfa + beta);
        double gamma = Math.acos((Math.pow(LBrazo, 2) + Math.pow(LAntebrazo, 2) - Math.pow(hipotenusa, 2)) / (2 * LBrazo * LAntebrazo));
        int angAnteBrazo = (int) -(gamma - 180);
        int angulos[] = {angAnteBrazo, angBrazo};
        System.out.println("a1 " + angAnteBrazo + "    a2 " + angBrazo);
        return angulos;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(20));
        g2d.drawLine(x, y, x + 100, y);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.red);

        int angulos[] = kinematic(p1-x, p2-y, LBrazo, LAnteBrazo);

        double point1[] = toCartesian(LAnteBrazo, angulos[0]);
        g2d.drawLine(x + 5, y + 5, x + (int) point1[0], y + (int) point1[1]);

        double point2[] = toCartesian(LBrazo, angulos[1]);
        g2d.drawLine(x + (int) point1[0], y + (int) point1[1], p1, p2);

    }

    public VierBotDriver() {
        initComponents();
        puerto = new ArduinoRXTX();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        p1 = evt.getX();
        p2 = evt.getY();
        repaint();
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}