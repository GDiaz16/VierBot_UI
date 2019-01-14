package vierbot_ui;

import java.awt.Graphics;
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
public class Scheduler extends Thread {

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

    ArduinoRXTX puerto;

    public Scheduler() {
        puerto = new ArduinoRXTX();
    }

    public int getxIP1() {
        return xIP1;
    }

    public int getyIP1() {
        return yIP1;
    }

    public int getpX1() {
        return pX1;
    }

    public int getpY1() {
        return pY1;
    }

    public int getxIP2() {
        return xIP2;
    }

    public int getyIP2() {
        return yIP2;
    }

    public int getpX2() {
        return pX2;
    }

    public int getpY2() {
        return pY2;
    }

    public int getxIP3() {
        return xIP3;
    }

    public int getyIP3() {
        return yIP3;
    }

    public int getpX3() {
        return pX3;
    }

    public int getpY3() {
        return pY3;
    }

    public int getxIP4() {
        return xIP4;
    }

    public int getyIP4() {
        return yIP4;
    }

    public int getpX4() {
        return pX4;
    }

    public int getpY4() {
        return pY4;
    }

    public int getxFrontal() {
        return xFrontal;
    }

    public void setpX1(int pX1) {
        this.pX1 = pX1;
    }

    public void setpY1(int pY1) {
        this.pY1 = pY1;
    }

    public void setpX2(int pX2) {
        this.pX2 = pX2;
    }

    public void setpY2(int pY2) {
        this.pY2 = pY2;
    }

    public void setpX3(int pX3) {
        this.pX3 = pX3;
    }

    public void setpY3(int pY3) {
        this.pY3 = pY3;
    }

    public void setpX4(int pX4) {
        this.pX4 = pX4;
    }

    public void setpY4(int pY4) {
        this.pY4 = pY4;
    }

    int xLateral = 150, y = 200, xFrontal = xLateral + 250, pX = 200, pY = 200;
    //valores geometricos
    int L2 = 70, L1 = 70;

    public void guardarCoordenadas(int q1, int q2) {
        System.out.print("{" + q1 + "," + q2 + "},");
    }

    boolean flag = false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            //System.out.println("run");
            if (flag) {
                animador();
                //puerto.protocol();
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    boolean pata1 = true;
    boolean pata2 = false;
    boolean pata3 = false;
    boolean pata4 = true;

    boolean mov1 = true;
    boolean mov2 = false;
    int angulos[][] = {{37, 87}, {33, 85}, {29, 81}, {27, 76}, {17, 82}, {9, 92}, {11, 98}, {7, 112}, {12, 115}, {18, 118}, {25, 119}, {34, 119}, {42, 118}, {51, 115}, {60, 112}, {69, 107}, {70, 98}, {78, 92}, {88, 75}, {100, 53}, {83, 70}, {75, 76}, {68, 81}, {61, 85}, {54, 87}, {48, 88}, {42, 88}, {37, 87}};

    public void rutaP1(int i) {
        double pointN1[] = toCartesian(getL1(), angulos[i][0]);
        double pointN2[] = toCartesian(getL1(), angulos[i][0] + angulos[i][1]);
        pX1 = (int) pointN2[0] + xIP1 + (int) pointN1[0];
        pY1 = (int) pointN2[1] + yIP1 + (int) pointN1[1];
        

    }

    public void rutaP2(int i) {
        double pointN1[] = toCartesian(getL1(), angulos[i][0]);
        double pointN2[] = toCartesian(getL1(), angulos[i][0] + angulos[i][1]);
        pX2 = (int) pointN2[0] + xIP2 + (int) pointN1[0];
        pY2 = (int) pointN2[1] + yIP2 + (int) pointN1[1];

    }

    public void rutaP3(int i) {
        double pointN1[] = toCartesian(getL1(), angulos[i][0]);
        double pointN2[] = toCartesian(getL1(), angulos[i][0] + angulos[i][1]);
        pX3 = (int) pointN2[0] + xIP3 + (int) pointN1[0];
        pY3 = (int) pointN2[1] + yIP3 + (int) pointN1[1];
    }

    public void rutaP4(int i) {
        double pointN1[] = toCartesian(getL1(), angulos[i][0]);
        double pointN2[] = toCartesian(getL1(), angulos[i][0] + angulos[i][1]);
        pX4 = (int) pointN2[0] + xIP4 + (int) pointN1[0];
        pY4 = (int) pointN2[1] + yIP4 + (int) pointN1[1];
        //q1
        //puerto.sendData(5, angulos[i][0]);
        //q2
        //puerto.sendData(0, angulos[i][1]+80);
    }
    int c1 = 0;
    int c2 = angulos.length / 2;

    public void animador() {
        int i = 0;
        long inicio = System.currentTimeMillis();
        long periodo = 100;
        while (i < angulos.length) {
            if (System.currentTimeMillis() - inicio > periodo) {
                if (c1 < angulos.length) {
                    rutaP1(c1);
                    rutaP4(c1);
                    c1++;
                } else {
                    c1 = 0;
                }
                if (c2 < angulos.length) {
                    rutaP2(c2);
                    rutaP3(c2);
                    c2++;
                } else {
                    c2 = 0;
                }

                inicio = System.currentTimeMillis();
                i++;
            }
        }

        if (mov1) {
            pata1 = true;
            pata2 = false;
            pata3 = false;
            pata4 = true;

            mov1 = false;
            mov2 = true;

        } else if (mov2) {
            pata1 = false;
            pata2 = true;
            pata3 = true;
            pata4 = false;

            mov2 = false;
            mov1 = true;
        }
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

    public double[] cinematicaD(double q1, double q2) {
        q1 = q1 * Math.PI / 180;
        q2 = q2 * Math.PI / 180;
        double h = -Math.sqrt(Math.pow(L1, 2) + Math.pow(L2, 2) - 2 * L1 * L2 * Math.cos(q2));
        double q4 = Math.acos((Math.pow(L2, 2) - Math.pow(L1, 2) - Math.pow(h, 2)) / (-2 * L1 * h));
        double q3 = q1 - q4;

        double x = Math.cos(q3) * h;// =L1*Math.cos(q1)+L2*Math.cos(q1-q2);
        double y = Math.sin(q3) * h; //= L1*Math.sin(q1)+L2*Math.sin(q1-q2);
        double xy[] = {x, y};
        return xy;
    }

    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
