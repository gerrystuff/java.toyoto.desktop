package jv.model.stations;

import TESTING.HILOS.Semaforo;

import java.awt.*;

public abstract class StationManager extends Canvas {
    public static Semaforo semaforos[];
    public static Semaforo s0, s1, s1t, s2, s3, s4, s5, newCar, flag0,flag1;
    protected int position;
    protected int line;
    protected Semaforo canWork;
    protected int workingOn;
    protected int nextStation ;
    protected int previusStation;
    protected String veh =  "";
    boolean robotOn = false;
    boolean robotOn2 = false;
    public boolean end = false;
    Graphics g;
    Image buffer = null;

    protected StationManager(int pos, int line) {
        this.position = pos;
        this.line = line;

        nextStation = position + 1;
        previusStation = position - 1;
        if (semaforos == null) {

            semaforos = new Semaforo[7];

            semaforos[0] = new Semaforo(5);
            semaforos[1] = new Semaforo(4);
            semaforos[2] = new Semaforo(2);
            semaforos[3] = new Semaforo(3);
            semaforos[4] = new Semaforo(3);
            semaforos[5] = new Semaforo(2);
            semaforos[6] = new Semaforo(5);
            newCar = new Semaforo(1);

            flag0 = new Semaforo(1);
            flag1 = new Semaforo(1);
        }

        if (position == 0)
            canWork = new Semaforo(1);
        else
            canWork = new Semaforo(0);

    }

    abstract void Dibuja();

    public void paint(Graphics g){
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
            this.g = buffer.getGraphics();
            repaint();
            return;
        }


        Dibuja();
        g.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);

    }


    public void update(Graphics g){
        paint(g);
    }


}
