package V3;



import MASTER.model.Rutinas;
import V3.controller.StationController;
import V3.model.Semaforo;

import java.awt.*;

public class Station extends StationController implements Runnable {

    static Semaforo s1,s2,s3,s4,s5,s6;
    static Semaforo trigger2,trigger3,trigger4,trigger5,trigger6;
    boolean working = false;
    Graphics g;
    Image buffer = null;


    public Station(int pos) {
        super(pos);
        if (s1 == null) {

            s1 = new Semaforo(5);
            s2 = new Semaforo(6);
            s3 = new Semaforo(3);
            s4 = new Semaforo(3);
            s5 = new Semaforo(2);
            s6 = new Semaforo(5);

            trigger2 = new Semaforo(0);
            trigger3 = new Semaforo(0);
            trigger4 = new Semaforo(0);
            trigger5 = new Semaforo(0);
            trigger6 = new Semaforo(0);

        }

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

    public void Dibuja(){
        g.drawImage(Rutinas.AjustarImagen(image,getWidth(),getHeight()).getImage(),0,0,null);
        if(working){
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 100, 45, null);
        }
    }

    @Override
    public void run() {


        switch (position) {
         case 1:
             s1.Espera();
            try {
                System.out.println("Trabajando en el chasis");
                working = true;
                repaint();
                Thread.sleep(3200);
            } catch (InterruptedException e) { e.printStackTrace(); }

             working = false;
             repaint();
            trigger2.Libera();
            break;

            case 2:
                trigger2.Espera();
            s2.Espera();
            s1.Libera();
                try {
                    System.out.println("Trabajando en el motor");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) { e.printStackTrace(); }

                working = false;
                repaint();
                trigger3.Libera();
                break;

            case 3:
                trigger3.Espera();
                s3.Espera();
                s2.Libera();
                try {
                    System.out.println("Trabajando en el carroceria");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) { e.printStackTrace(); }

                working = false;
                repaint();
                trigger4.Libera();
                break;
            case 4:
                trigger4.Espera();
                s4.Espera();
                s3.Libera();
                try {
                    System.out.println("Trabajando en las llantas");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) { e.printStackTrace(); }

                working = false;
                repaint();
                trigger5.Libera();
                break;
            case 5:
                trigger5.Espera();
                s5.Espera();
                s4.Libera();
                try {
                    System.out.println("Trabajando en el interiores");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) { e.printStackTrace(); }

                working = false;
                repaint();
                trigger6.Libera();
                break;
            case 6:
                trigger6.Espera();
                s6.Espera();
                s5.Libera();
                try {
                    System.out.println("Trabajando en las pruebas ");
                    working = true;
                    repaint();
                    Thread.sleep(3200);
                } catch (InterruptedException e) { e.printStackTrace(); }

                System.out.println("ia mevoi..");
                working = false;
                repaint();
                break;

        }
    repaint();
    }


    }



