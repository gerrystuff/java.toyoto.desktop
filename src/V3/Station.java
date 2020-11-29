package V3;



import MASTER.model.Rutinas;
import V3.controller.StationController;
import V3.model.Semaforo;

import java.awt.*;

public class Station extends StationController implements Runnable {

    public static Semaforo s1, s2, s3, s4, s5, s6, trigger;
    public Semaforo work;
    static Station matriz[][];
    int vehiculos;

    boolean working = false;
    Graphics g;
    Image buffer = null;


    public Station(int pos, int line) {
        super(pos, line);
        if (s1 == null) {


            s1 = new Semaforo(5);
            s2 = new Semaforo(6);
            s3 = new Semaforo(3);
            s4 = new Semaforo(3);
            s5 = new Semaforo(2);
            s6 = new Semaforo(5);

            vehiculos = 0;
        }

        if (position == 0)
            work = new Semaforo(1);
        else
            work = new Semaforo(0);

    }

    public void paint(Graphics g) {
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
            this.g = buffer.getGraphics();
            repaint();
            return;
        }


        Dibuja();
        g.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);


    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void Dibuja() {
        g.drawImage(Rutinas.AjustarImagen(image, getWidth(), getHeight()).getImage(), 0, 0, null);
        if (working) {
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 100, 45, null);
        }
    }

    @Override
    public void run() {
        System.out.println(getName() + "Inicia linea de produccion");



        switch (position) {
            case 1:
                try {
                    System.out.println("Trabajando en el chasis");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                working = false;
                repaint();
                break;

            case 2:
                try {
                    System.out.println("Trabajando en el motor");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                working = false;
                repaint();
                break;

            case 3:
                try {
                    System.out.println("Trabajando en el carroceria");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                working = false;
                repaint();
                break;
            case 4:
                try {
                    System.out.println("Trabajando en las llantas");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                working = false;
                repaint();
                break;
            case 5:
                s5.Espera();
                s4.Libera();
                try {
                    System.out.println("Trabajando en el interiores");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                working = false;
                repaint();
                break;
            case 6:
                try {
                    System.out.println("Trabajando en las pruebas ");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("ia mevoi..");
                working = false;
                repaint();
                break;

        }
        repaint();
    }
}




