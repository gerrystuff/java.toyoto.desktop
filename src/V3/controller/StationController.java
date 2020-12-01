package V3.controller;

import MASTER.model.Robot;
import V3.Station;

import java.awt.*;

public abstract class StationController extends Canvas {
    protected String name;
    protected String image = "";
    protected int position;
    protected int line;

    protected StationController(int pos,int line) {
        this.position = pos;
        this.line = line;
        setData();
        setName("Linea " + line);
    }

    private void setData(){
        switch (position){
            case 0: name = "Chasis";
                image = "chasis.png";
                break;
            case 1: name = "Motor-Transmision";
                image = "engine.png";
                break;
            case 2: name = "Carroceria";
                image = "bodywork.png";
                break;
            case 3: name = "Interiores";
                image = "inside.png";
                break;
            case 4: name = "Llantas";
                image = "tires.png";
                break;
            case 5: name = "Pruebas";
                image = "testing.png";

        }
    }

}
