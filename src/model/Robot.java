package model;

public class Robot {
    private Station estacion;

    public Robot(Station e ){
        estacion = e;
    }

    public String toString(){
        return "model.Robot trabajando en la estacion : " + estacion.getName();
    }

}
