package TESTING.letseeing;

import TESTING.MASTER.controller.StationController;

public class Station extends StationController implements Runnable {
    static Semaforo s1 = null;


    protected Station(int pos) {
        super(pos);
        if(s1 == null)
            this.s1 = new Semaforo(pos);

    }

    @Override
    public void run() {

        s1.Espera();
        try {
            System.out.println(getName()+": Estacion trabajando");
            Thread.sleep(2500);

        } catch (InterruptedException e) {  e.printStackTrace();
        }s1.Libera();
    }


}
