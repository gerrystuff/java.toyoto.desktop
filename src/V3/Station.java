package V3;



import HILOS.Semaforo;
import MASTER.model.Rutinas;
import V3.controller.StationController;

import java.awt.*;

public class Station extends StationController implements Runnable {

    private int plusy;
    private int plusx;
    static Semaforo s1,s2;
boolean working = false;

    public Station(int pos){
        super(pos);
    if(s1 == null) {

        s1 = new Semaforo(5);
        s2 = new Semaforo(4);

    }
        plusy = 0;
        plusx = 0;
    }

    public void paint(Graphics g){

            g.drawImage(Rutinas.AjustarImagen(image,getWidth(),getHeight()).getImage(),0,0,null);
            if(working){
                g.drawImage(Rutinas.AjustarImagen("robot.png", 20, 20).getImage(), 135- plusx, 15+ plusy, null);
            }
            //
//        for(int i = 1; i <= robots.length; i++) {
//            g.drawImage(Rutinas.AjustarImagen("robot.png", 20, 20).getImage(), 135- plusx, 15+ plusy, null);
//
//            if(i == 2 || i == 4) {
//                plusy = 0;
//                plusx += 20;
//            }
//            else
//                plusy += 20;
//        }
//
//        plusy = 0;
//        plusx = 0;
    }


    @Override
    public void run() {
        System.out.println("ENTRA CARRO ");

        s1.Espera();
        try {
            System.out.println(this.getName()+": Linea  esta instalando chasis ");
            working = true;
            repaint();
            Thread.sleep(2200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        working = false;
        repaint();
        s1.Libera();


    }


}
