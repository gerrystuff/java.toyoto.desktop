package controller;

import model.Robot;

public abstract class StationController {
    protected String name;
    protected int position;
    protected int qtyServices;
    protected int time1, time2;
    protected Robot robots[];

    protected StationController(int pos) {
        this.position = pos;
        setData();
    }

    private void setData(){
        switch (position){
            case 1: name = "Chasis";
                time1 = 20;
                robots = new Robot[5];
                break;
            case 2: name = "Motor-Transmision";
                time1 = 6;
                time2 = 4;
                robots = new Robot[4];
                break;
            case 3: name = "Carroceria";
                time1 = 10;
                robots = new Robot[4];
                break;
            case 4: name = "Interiores";
                time1 = 5;
                robots = new Robot[3];
                break;
            case 5: name = "Llantas";
                time1 = 5;
                robots = new Robot[2];
                break;
            case 6: name = "Pruebas";
                time1 = 10;
                robots = new Robot[5];

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
