/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vierbot_ui;

import com.sun.glass.events.KeyEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Gonzalo Diaz
 */
public class VierBotDriver extends javax.swing.JPanel {

    //coordenadas iniciales pierna 1
    int xIP1 = 150, yIP1 = 200;
    //puntero pierna 1
    int pX1 = 160, pY1 = 300;

    //coordenadas iniciales pierna 2
    int xIP2 = 250, yIP2 = 200;
    //puntero pierna 2
    int pX2 = 260, pY2 = 300;

    //coordenadas iniciales pierna 3
    int xIP3 = 150, yIP3 = 200;
    //puntero pierna 1
    int pX3 = 160, pY3 = 300;

    //coordenadas iniciales pierna 4
    int xIP4 = 250, yIP4 = 200;
    //puntero pierna 2
    int pX4 = 260, pY4 = 300;

    //vista frontal
    int xFrontal = 500, yFrontal = 200;

    int aux = 10;
    ArduinoRXTX puerto;
    Scheduler scheduler;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        //tronco
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawLine(xIP1, yIP1, xIP1 + 100, yIP1);
        g2d.drawLine(xFrontal, yIP1, xFrontal + 50, yIP1);
        g2d.setStroke(new BasicStroke(2));

        double angulosP1[] = scheduler.kinematic(pX1 - xIP1 + 5, pY1 - yIP1);
        double angulosP2[] = scheduler.kinematic(pX2 - xIP2 + 5, pY2 - yIP2);
        double angulosP3[] = scheduler.kinematic(pX3 - xIP3 + 5, pY3 - yIP3);
        double angulosP4[] = scheduler.kinematic(pX4 - xIP4 + 5, pY4 - yIP4);

        g2d.setColor(Color.red);
        //brazo 1
        double point1[] = scheduler.toCartesian(scheduler.getL1(), angulosP1[0]);
        g2d.drawLine(xIP1, yIP1 + 5, xIP1 + (int) point1[0], yIP1 + (int) point1[1] + 5);

        //brazo 2
        double point2[] = scheduler.toCartesian(scheduler.getL1(), angulosP2[0]);
        g2d.drawLine(xIP2, yIP2 + 5, xIP2 + (int) point2[0], yIP2 + (int) point2[1] + 5);

        g2d.setColor(Color.orange);
        //brazo 3
        double point3[] = scheduler.toCartesian(scheduler.getL1(), angulosP3[0]);
        g2d.drawLine(xIP3, yIP3 + 5, xIP3 + (int) point3[0], yIP3 + (int) point3[1] + 5);

        //brazo 4
        double point4[] = scheduler.toCartesian(scheduler.getL1(), angulosP4[0]);
        g2d.drawLine(xIP4, yIP4 + 5, xIP4 + (int) point4[0], yIP4 + (int) point4[1] + 5);

        g2d.setColor(Color.blue);
        //antebrazo 1
        g2d.drawLine(xIP1 + (int) point1[0], yIP1 + (int) point1[1] + 5, pX1, pY1);

        //antebrazo 2
        g2d.drawLine(xIP2 + (int) point2[0], yIP2 + (int) point2[1] + 5, pX2, pY2);

        g2d.drawOval(pX1, pY1, 5, 5);
        g2d.drawOval(pX2, pY2, 5, 5);

        g2d.setColor(Color.gray);
        //antebrazo 3
        g2d.drawLine(xIP3 + (int) point3[0], yIP3 + (int) point3[1] + 5, pX3, pY3);

        //antebrazo 4
        g2d.drawLine(xIP4 + (int) point4[0], yIP4 + (int) point4[1] + 5, pX4, pY4);

        g2d.drawOval(pX3, pY3, 5, 5);
        g2d.drawOval(pX4, pY4, 5, 5);

        g2d.setColor(Color.green);
        //brazo3 vista frontal
        g2d.drawLine(xFrontal - 10, yFrontal, xFrontal - 10, pY3);
        //brazo 2 vista frontal
        g2d.drawLine(xFrontal + 60, yFrontal, xFrontal + 60, pY2);

        g2d.setColor(Color.blue);
        //brazo 1 vista frontal
        g2d.drawLine(xFrontal + 60, yFrontal, xFrontal + 60, pY1);
        //brazo4 vista frontal
        g2d.drawLine(xFrontal - 10, yFrontal, xFrontal - 10, pY4);

    }

    public VierBotDriver() {
        initComponents();
        // puerto = new ArduinoRXTX();
        scheduler = new Scheduler();
        scheduler.writter();
        setFocusable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jCheckBox1.setText("Brazo 1");

        jCheckBox2.setText("Brazo 2");

        jCheckBox3.setText("Brazo 3");

        jCheckBox4.setText("Brazo 4");

        jButton1.setText("Guardar coordenada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox4)
                .addGap(195, 195, 195)
                .addComponent(jButton1)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jButton1))
                .addContainerGap(462, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_NUMPAD1:
                if (jCheckBox1.isSelected()) {
                    jCheckBox1.setSelected(false);
                } else {
                    jCheckBox1.setSelected(true);
                }
                System.out.println("presionado");
                break;
            case KeyEvent.VK_NUMPAD2:
                if (jCheckBox2.isSelected()) {
                    jCheckBox2.setSelected(false);
                } else {
                    jCheckBox2.setSelected(true);
                }
                break;
            case KeyEvent.VK_NUMPAD3:
                if (jCheckBox3.isSelected()) {
                    jCheckBox3.setSelected(false);
                } else {
                    jCheckBox3.setSelected(true);
                }
                break;
            case KeyEvent.VK_NUMPAD4:
                if (jCheckBox4.isSelected()) {
                    jCheckBox4.setSelected(false);
                } else {
                    jCheckBox4.setSelected(true);
                }
                break;
        }
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (jCheckBox1.isSelected()) {
                    pY1 -= aux;
                }
                if (jCheckBox2.isSelected()) {
                    pY2 -= aux;
                }
                if (jCheckBox3.isSelected()) {
                    pY3 -= aux;
                }
                if (jCheckBox4.isSelected()) {
                    pY4 -= aux;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (jCheckBox1.isSelected()) {
                    pY1 += aux;
                }
                if (jCheckBox2.isSelected()) {
                    pY2 += aux;
                }
                if (jCheckBox3.isSelected()) {
                    pY3 += aux;
                }
                if (jCheckBox4.isSelected()) {
                    pY4 += aux;
                }

                break;
            case KeyEvent.VK_RIGHT:
                if (jCheckBox1.isSelected()) {
                    pX1 += aux;
                }
                if (jCheckBox2.isSelected()) {
                    pX2 += aux;
                }
                if (jCheckBox3.isSelected()) {
                    pX3 += aux;
                }
                if (jCheckBox4.isSelected()) {
                    pX4 += aux;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (jCheckBox1.isSelected()) {
                    pX1 -= aux;
                }
                if (jCheckBox2.isSelected()) {
                    pX2 -= aux;
                }
                if (jCheckBox3.isSelected()) {
                    pX3 -= aux;
                }
                if (jCheckBox4.isSelected()) {
                    pX4 -= aux;
                }
                break;
        }

        if(evt.getKeyCode() == KeyEvent.VK_G){
            jButton1.doClick();
        }
        repaint();
    }//GEN-LAST:event_formKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    // End of variables declaration//GEN-END:variables
}
