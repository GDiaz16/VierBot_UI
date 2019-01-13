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

    int xLateral = 150, y = 200, xFrontal = xLateral + 250, x1 = 10, y1 = 30, pX = 200, pY = 200;
    //int valores[][] = {{92, 197}, {92, 196}, {92, 195}, {93, 195}, {94, 195}, {95, 195}, {96, 195}, {97, 195}, {98, 195}, {99, 195}, {100, 195}, {101, 195}, {102, 195}, {103, 195}, {104, 195}, {105, 195}, {106, 195}, {107, 195}, {108, 195}, {109, 195}, {109, 194}, {109, 193}, {109, 192}, {110, 192}, {111, 192}, {112, 192}, {113, 192}, {114, 192}, {115, 192}, {116, 192}, {117, 192}, {118, 192}, {119, 192}, {120, 192}, {120, 191}, {121, 191}, {122, 191}, {123, 191}, {124, 191}, {125, 191}, {126, 191}, {127, 191}, {128, 191}, {129, 191}, {130, 191}, {131, 191}, {132, 191}, {133, 191}, {134, 191}, {135, 191}, {135, 192}, {135, 193}, {135, 194}, {135, 195}, {135, 196}, {135, 197}, {135, 198}, {136, 198}, {137, 198}, {138, 198}, {138, 199}, {138, 200}, {138, 201}, {138, 202}, {138, 203}, {138, 204}, {138, 205}, {138, 206}, {138, 207}, {138, 208}, {138, 209}, {138, 210}, {138, 211}, {138, 212}, {138, 213}, {138, 214}, {138, 215}, {138, 216}, {138, 217}, {138, 218}, {138, 219}, {138, 220}, {138, 221}, {138, 222}, {138, 223}, {138, 224}, {138, 225}, {138, 226}, {138, 227}, {137, 227}, {136, 227}, {135, 227}, {134, 227}, {133, 227}, {132, 227}, {131, 227}, {130, 227}, {129, 227}, {128, 227}, {127, 227}, {126, 227}, {125, 227}, {124, 227}, {124, 226}, {123, 226}, {122, 226}, {121, 226}, {120, 226}, {119, 226}, {118, 226}, {117, 226}, {116, 226}, {115, 226}, {114, 226}, {113, 226}, {112, 226}, {111, 226}, {110, 226}, {109, 226}, {108, 226}, {107, 226}, {106, 226}, {105, 226}, {104, 226}, {103, 226}, {102, 226}, {101, 226}, {100, 226}, {99, 226}, {98, 226}, {97, 226}, {96, 226}, {95, 226}, {94, 226}, {93, 226}, {92, 226}, {91, 226}, {90, 226}, {89, 226}, {89, 225}, {89, 224}, {89, 223}, {89, 222}, {89, 221}, {89, 220}, {89, 219}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}, {89, 218}};

    //valores geometricos
    int L2 = 70, L1 = 70;
    ArduinoRXTX puerto;

    public double distance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double[] toCartesian(double r, double angle) {
        double point[] = new double[2];
        point[0] = r * Math.cos(angle * Math.PI / 180);
        point[1] = r * Math.sin(angle * Math.PI / 180);

        return point;
    }

    public double[] cinematicaD(double q1, double q2) {
        //double h = Math.sqrt(Math.pow(L1, 2) + Math.pow(L2, 2) - 2 * L1 * L2 * Math.cos(q2));
        //double q4 = Math.acos((Math.pow(L2, 2) - Math.pow(L1, 2) - Math.pow(h, 2)) / (-2 * L1 * h));
        //double q3 = q1 - q4;
//        double y1 = Math.sin(q3) * h;
//        double x1 = Math.cos(q3) * h;
        double x0 = L1 * Math.cos(q1);
        double y0 = L1 * Math.sin(q1);

        double x1 = L1 * Math.cos(q1) + L2 * Math.cos(q1 - q2);
        double y1 = L1 * Math.sin(q1) + L2 * Math.sin(q1 - q2);
        double point[] = {x1, y1};
        return point;
    }

//cinematica para un brazo con dos grados de libertad
    public int[] kinematic(int finalX, int finalY, int LBrazo, int LAntebrazo) {
        double hipotenusa = Math.sqrt(Math.pow(finalX, 2) + Math.pow(finalY, 2));
        double alfa = Math.atan2(finalY, finalX);
        double beta = Math.acos((Math.pow(LBrazo, 2) - Math.pow(LAntebrazo, 2) + Math.pow(hipotenusa, 2)) / (2 * LBrazo * hipotenusa));
        int angBrazo = (int) ((alfa + beta) * (180 / Math.PI));
        double gamma = Math.acos((Math.pow(LBrazo, 2) + Math.pow(LAntebrazo, 2) - Math.pow(hipotenusa, 2)) / (2 * LBrazo * LAntebrazo));
        int angAnteBrazo = (int) ((gamma - 180) * (180 / Math.PI));
        int angulos[] = {angAnteBrazo, angBrazo};
        //System.out.println("a1 " + angAnteBrazo + "    a2 " + angBrazo);
        return angulos;
    }

    //punto en R3
    //double px,py,pz;
    //angulo hombro
    double q1;
    //coseno de q3
    double cosq3;
    //seno de q3
    double sinq3;
    //angulo antebrazo
    double q3;
    //angulo brazo
    double q2;
    //angulos formados por la extremidad
    double alpha, beta;
    //radio brazo
    double radioBrazo;

    public double[] cinematica(double px, double py) {
        double q1 = Math.atan2(py, px);
        double point1[] = toCartesian(L1, q1);
        double q2 = Math.atan2(py + point1[1], px + point1[0]);

        double angulos[] = {q1 * (180 / Math.PI), q2 * (180 / Math.PI)};
        return angulos;
    }

    public double[] cinematica2(double px, double py) {
        //System.out.println("xm= "+px+" ym= "+py);
        // q1 = Math.atan(py / pz);

        // double r= Math.sqrt(Math.pow(LAnteBrazo, 2) + Math.pow(LBrazo, 2));
        double cosq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(L1, 2) - Math.pow(L2, 2)) / (2 * L2 * L1);
        // double cosq2 = (Math.pow(r, 2) - Math.pow(LBrazo, 2) - Math.pow(LAnteBrazo, 2)) / (2* LBrazo*r);
//System.out.print("cosq3 " + cosq3+" ");
        double sinq2 = Math.sqrt(1 - Math.pow(cosq2, 2));

        double q2 = Math.atan2(sinq2, cosq2);
        //double q2 = Math.atan((Math.sqrt(1- Math.pow(cosq2, 2)))/(cosq2));    
        // double q2=Math.acos((Math.pow(r, 2) - Math.pow(LBrazo, 2) - Math.pow(LAnteBrazo, 2)) / (2* LBrazo*r));

        double q1 = Math.atan2(py, px) - Math.atan2(L2 * sinq2, L1 + L2 * cosq2);
        // double q1 = Math.atan(py/px)-Math.atan((LAnteBrazo*Math.sin(q2))/(LBrazo+LAnteBrazo*cosq2));
        // double q1=Math.atan((pY*(L2*Math.cos(q2)+L1)-pX*L2*Math.sin(q2))               /(pX*(L2*Math.cos(q2)+L1)+pY*L2*Math.sin(q2)));
        double angulos[] = {q1 * (180 / Math.PI), q2 * (180 / Math.PI)};
        return angulos;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(20));
        g2d.drawLine(xLateral, y, xLateral + 100, y);
        g2d.drawLine(xFrontal, y, xFrontal + 50, y);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.red);

        double angulos[] = cinematica2(pX - xLateral + 5, pY - y);

        System.out.println("q1=" + angulos[0] + "   q2=" + angulos[1]);
        //brazo
        double point1[] = toCartesian(L1, angulos[0]);
        g2d.drawLine(xLateral, y + 5, xLateral + (int) point1[0], y + (int) point1[1] + 5);

        //antebrazo
        g2d.drawLine(xLateral + (int) point1[0], y + (int) point1[1] + 5, pX, pY);

        g2d.setColor(Color.blue);
        g2d.drawOval(pX, pY, 5, 5);

    }

    public VierBotDriver() {
        initComponents();
        // puerto = new ArduinoRXTX();
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

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
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
        pX = evt.getX();
        pY = evt.getY();
        repaint();

        //System.out.println("moved");
    }//GEN-LAST:event_formMouseMoved
    int aux = 1;
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        //int aux = 10;

    }//GEN-LAST:event_formKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        requestFocusInWindow();
    }//GEN-LAST:event_formMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                pY -= aux;
                break;
            case KeyEvent.VK_DOWN:
                pY += aux;
                break;
            case KeyEvent.VK_RIGHT:
                pX += aux;
                break;
            case KeyEvent.VK_LEFT:
                pX -= aux;
                break;

        }

        repaint();
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
