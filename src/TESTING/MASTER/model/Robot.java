package TESTING.MASTER.model;


public class Robot {
    private Station estacion;

    public Robot(Station e ){
        estacion = e;
    }

    public String toString(){
        return "Robot trabajando en la estacion : " + estacion.getName();
    }

}
