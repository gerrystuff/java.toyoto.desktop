package HILOS.TESTING.CARRERA;

import HILOS.Semaforo;

public class Carrera {
    static Semaforo s1,s2;
    Thread lieb,tort;
    boolean estado;
    Puente puente;
    public Carrera(){
        s1 = new Semaforo(1);
        s2 = new Semaforo(0);

        estado = false;

        lieb = new Thread(new Liebre());
        tort = new Thread(new Tortuga());

        puente = new Puente(100,150);

    }


         class Liebre implements Runnable {
            int km = 0;
             @Override
             public void run() {
                 int sprints = 1;

                 while(km<150){
                     km ++;
                     s1.Espera();
                     System.out.println("Liebree en km #"+km + "\tSprint: "+sprints);
                     s2.Libera();
                     sprints++;

                 }

                 System.out.println("Ya llego la perra esta estupida.");
             }
         }


         class Tortuga implements Runnable{
            int km = 0 ;
            @Override
            public void run() {

                int sprints = 1;

                while(km<150){
                    km++;
                    s2.Espera();
                    System.out.println("Tortuga en km #"+km + "\tSprint: "+sprints);
                    sprints++;
                    s1.Libera();
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
