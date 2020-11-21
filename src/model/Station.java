package model;

import controller.StationController;

import java.awt.*;

public class Station extends StationController implements Runnable{

    public Station(int pos){
        super(pos);

        for(int i = 0; i<robots.length; i ++)
            robots[i] = new Robot(this);
    }


    @Override
    public void run() {

    }

}
