package HILOS.TESTING.CARRERA;

import HILOS.Semaforo;

public class Carrera {
    static Semaforo s1,s2;
    Thread lieb,tort;
    public boolean puenteActivo;
    public Carrera(){
        s1 = new Semaforo(1);
        s2 = new Semaforo(0);

        puenteActivo = false;

        lieb = new Thread(new Liebre());
        tort = new Thread(new Tortuga());
        }


         class Liebre implements Runnable {
            int km;
             @Override
             public void run() {
                 int sprints = 1;

                 while(km <= 150){
                     if(km>=50 && km <=100){ //puente entre km 50 y 100 y esta desocupado
                         if(!puenteActivo)
                         puenteActivo = true; //el puente se ocupa
                         else {
                             s1.Espera();
                             puenteActivo = true;
                         }
                         }
                     if(km>=100 && puenteActivo){
                         puenteActivo = false;
                         s1.Libera();
                     }

                     km += 2;
                     System.out.println("Liebree en km #"+km + "\tSprint: "+sprints);
                     sprints++;

                 }

                 System.out.println("Ya llego la perra esta.");


//                 for (int i = 0; i < 20; i++) {
//                     s1.Espera();
//                     System.out.println("1");
//                     s2.Libera();
//                 }
             }
         }

         class Tortuga implements Runnable{
            int km ;
            @Override
            public void run() {

                int sprints = 1;

                while(km<=100){
                    km++;
                    System.out.println("Tortuga en km #"+km + "\tSprint: "+sprints);
                    sprints++;

                }

                System.out.println("Ya llego el pinche lento");



//                for(int i=0 ; i<20 ;i++) {
//                    s2.Espera();
//                    System.out.println("0");
//                    s1.Libera();
//                }
            }
        }

        public void start(){
        lieb.start();
        tort.start();
        }

}
