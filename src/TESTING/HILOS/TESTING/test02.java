package TESTING.HILOS.TESTING;

import TESTING.HILOS.Semaforo;

public class test02 {
    static Semaforo s1,s2;
    Thread pi,po;
    public test02(){
        s1 = new Semaforo(1);
        s2 = new Semaforo(0);

        pi = new Thread(new ping());
        po = new Thread(new pong());
    }


    class ping implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                s1.Espera();
                System.out.println("1");
                s2.Libera();
            }
        }
    }

    class pong implements Runnable{

        @Override
        public void run() {
            for(int i=0 ; i<20 ;i++) {
                s2.Espera();
                System.out.println("0");
                s1.Libera();
            }
        }
    }

    public void start(){
        pi.start();
        po.start();
    }

}
