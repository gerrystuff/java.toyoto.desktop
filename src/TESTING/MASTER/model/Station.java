package TESTING.MASTER.model;

import TESTING.MASTER.controller.StationController;

import java.awt.*;

/*
@startuml
 class Station extends StationManager implements Runnable{

 - plusy : int
 - plusx : int

 + Station (pos : int)
 + paint () : void
 + run () : void

}
@enduml
 */
public class Station extends StationController implements Runnable{

    private int plusy;
    private int plusx;


    public Station(int pos){
        super(pos);

        plusy = 0;
        plusx = 0;

        for(int i = 0; i<robots.length; i ++)
            robots[i] = new Robot(this);
    }

    public void paint(Graphics g){
        g.drawImage(Rutinas.AjustarImagen(image,getWidth(),getHeight()).getImage(),0,0,null);

        for(int i = 1; i <= robots.length; i++) {
            g.drawImage(Rutinas.AjustarImagen("robot.png", 20, 20).getImage(), 135- plusx, 15+ plusy, null);

            if(i == 2 || i == 4) {
                plusy = 0;
                plusx += 20;
            }
            else
                plusy += 20;
        }

        plusy = 0;
        plusx = 0;
    }


    @Override
    public void run() {

    }

}
