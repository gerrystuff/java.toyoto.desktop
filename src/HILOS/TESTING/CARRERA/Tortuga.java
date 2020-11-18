package HILOS.TESTING.CARRERA;

import java.util.Random;

public class Tortuga extends Thread {
    private int km;
    private Random r;

    public void run(){

        while(km<10){
            km++;
            System.out.println("Tortuga  kilometro # " + km);
        }

        System.out.println("Tortuga llego ala meta");

    }
}
