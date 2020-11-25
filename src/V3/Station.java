package V3;



import MASTER.model.Rutinas;
import V3.controller.StationController;
import V3.model.Semaforo;

import java.awt.*;

public class Station extends StationController implements Runnable {

    private int plusy;
    private int plusx;
    static Semaforo s1,s2;
    boolean working = false;
    Graphics g;
    Image buffer = null;
    public Station(int pos){
        super(pos);
    if(s1 == null) {

        s1 = new Semaforo(1);
        s2 = new Semaforo(1);

    }
        plusy = 0;
        plusx = 0;
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
            g.drawImage(Rutinas.AjustarImagen("robot.png", 20, 20).getImage(), 135- plusx, 15+ plusy, null);
        }
    }

    @Override
    public void run() {
        System.out.println("Arancha estacion. ");

        s1.Espera();

        try {
            System.out.println(this.getName()+": Linea  esta instalando chasis ");
            working = true;
            repaint();
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        working = false;
        repaint();
        s1.Libera();

        s2.Espera();
        try {
            System.out.println(this.getName()+": Linea  esta instalando motor");
            working = true;
            repaint();
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        working = false;
        repaint();
        s2.Libera();


    }


}
