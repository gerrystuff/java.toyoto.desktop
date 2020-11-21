package controller;

import model.Robot;
import model.Rutinas;

import java.awt.*;

public abstract class StationController extends Canvas {
    protected String name;
    private String image = "";
    protected int position;
    protected int qtyServices;
    protected int time1, time2;
    protected Robot robots[];

    protected StationController(int pos) {
        this.position = pos;
        setData();
    }

    public void paint(Graphics g){
        g.drawImage(Rutinas.AjustarImagen(image,getWidth(),getHeight()).getImage(),0,0,null);

    }
    private void setData(){
        switch (position){
            case 1: name = "Chasis";
                time1 = 20;
                robots = new Robot[5];
                image = "chasis.png";
                break;
            case 2: name = "Motor-Transmision";
                time1 = 6;
                time2 = 4;
                robots = new Robot[4];
                image = "engine.png";
                break;
            case 3: name = "Carroceria";
                time1 = 10;
                robots = new Robot[4];
                image = "bodywork.png";
                break;
            case 4: name = "Interiores";
                time1 = 5;
                robots = new Robot[3];
                image = "inside.png";
                break;
            case 5: name = "Llantas";
                time1 = 5;
                robots = new Robot[2];
                image = "tires.png";
                break;
            case 6: name = "Pruebas";
                time1 = 10;
                robots = new Robot[5];
                image = "testing.png";

        }
        if(position == 2)
            qtyServices = 2;
        else
            qtyServices = 1;
    }

    public String getName(){
        return name;
    }
}
