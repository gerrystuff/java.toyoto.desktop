package model;

import controller.StationController;

public class Station extends StationController implements Runnable{
    public Station(int pos){
        super(pos);

        for(int i = 0; i<robots.length; i ++)
            robots[i] = new Robot(this);

    }


    public void showRobots(){
        for(Robot element: robots)
            System.out.println(element.toString());
    }

    @Override
    public void run() {

    }

}
