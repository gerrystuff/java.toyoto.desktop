package V3.controller;

import MASTER.model.Robot;
import V3.Station;

import java.awt.*;

public abstract class StationController extends Canvas {
    protected String name;
    protected String image = "";
    protected int position;
    protected int time1, time2;
    protected int line;
    protected Robot robots[];

    protected StationController(int pos,int line) {
        this.position = pos;
        this.line = line;
        setData();
        setName("Linea " + line);
    }

    private void setData(){
        switch (position){
            case 0: name = "Chasis";
                time1 = 20;
                robots = new Robot[5];
                image = "chasis.png";
                break;
            case 1: name = "Motor-Transmision";
                time1 = 6;
                time2 = 4;
                robots = new Robot[6];
                image = "engine.png";
                break;
            case 2: name = "Carroceria";
                time1 = 10;
                robots = new Robot[3];
                image = "bodywork.png";
                break;
            case 3: name = "Interiores";
                time1 = 5;
                robots = new Robot[3];
                image = "inside.png";
                break;
            case 4: name = "Llantas";
                time1 = 5;
                robots = new Robot[2];
                image = "tires.png";
                break;
            case 5: name = "Pruebas";
                time1 = 10;
                robots = new Robot[5];
                image = "testing.png";

        }
    }

}
