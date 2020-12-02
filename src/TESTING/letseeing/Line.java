package TESTING.letseeing;

public class Line extends Thread{
    Station estaciones[];
    Semaforo s ;

    /*
    @startuml
    (*)  --> [constructor crea] "Estaciones"
    @enduml
     */
    public Line(){
        estaciones = new Station[6];
        for(int i = 0; i < estaciones.length ;i++)
            estaciones[i] = new Station(i+1);
    }

    public void run(){
    }
}
