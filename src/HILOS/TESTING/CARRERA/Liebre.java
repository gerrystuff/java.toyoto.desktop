package HILOS.TESTING.CARRERA;

import java.util.Random;

public class Liebre extends Thread{
    private int km;
    private Random r;

    public Liebre(){
        km  = 0;
        r = new Random();
    }
    public void run(){
        int sprints = 0;

        while(km<10){
            km += r.nextInt(2)+1;
            sprints++;

            if(sprints%2==0) {
                try {
                    sleep(500);
                    System.out.println("La liebre se durmio.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("Liebre km # " + km);

        }

        System.out.println("La libre ha finalizado");
    }

}
