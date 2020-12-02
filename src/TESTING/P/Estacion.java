package TESTING.P;

import TESTING.HILOS.Semaforo;

public class Estacion extends Thread {
    int linea, estacion;
    static Estacion[][] mEst;
    Semaforo work;
    boolean f = false;
    static Semaforo se1 = null;
    static Semaforo se2, se2t, se3, se4, se5, se6, trigger;
    static int contProduccion;
    private char tipo;

    public int getLinea() {
        return linea;
    }

    public int getEstacion() {
        return estacion;
    }

    public Estacion(char tipo, int linea, int estacion) {
        this.tipo = tipo;
        this.linea = linea;
        this.estacion = estacion;
        if (tipo == 'C')
            this.work = new Semaforo(1);
        else
            this.work = new Semaforo(0);

        if (se1 == null) {
            se1 = new Semaforo(5);
            se2 = new Semaforo(4);
            se2t = new Semaforo(2);
            se3 = new Semaforo(3);
            se4 = new Semaforo(3);
            se5 = new Semaforo(2);
            se6 = new Semaforo(5);
            trigger = new Semaforo(1);

            contProduccion = 0;


        }

    }

    public void run() {
        System.out.println(getName() + "Inicia linea de produccion");
        while (true) {
            switch (tipo) {
                case 'C':
                    Linea.getEst()[linea][estacion].work.Espera();
                    se1.Espera();
                    trigger.Espera();
                    if (contProduccion == 25) {
                        Linea.getEst()[linea][estacion + 1].f = true;
                        Linea.getEst()[linea][1].work.Libera();

                        trigger.Libera();
                        se1.Libera();
                        Linea.getEst()[linea][0].stop();
                    }
                    contProduccion++;

                    System.out.println(linea + "Frabicando el carro #" + contProduccion);
                    trigger.Libera();

                    System.out.println("La linea " + linea + "esta instalando el chasis");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (Linea.getEst()[linea][estacion + 1].isAlive()) {
                        Linea.getEst()[linea][estacion + 1].start();
                        Linea.getEst()[linea][estacion + 1].work.Libera();
                    } else
                        Linea.getEst()[linea][estacion + 1].work.Libera();

                    se1.Libera();
                    break;

                case 'R':

                    Linea.getEst()[linea][estacion].work.Espera();
                    if (Linea.getEst()[linea][estacion].f && !Linea.getEst()[linea][estacion - 1].isAlive()) {
                        Linea.getEst()[linea][estacion + 1].f = true;
                        Linea.getEst()[linea][estacion + 1].work.Libera();

                        Linea.getEst()[linea][estacion].stop();
                    }

                    se2.Espera();
                    Linea.getEst()[linea][estacion - 1].work.Libera();
                    System.out.println("La linea " + linea + " esta instalando el motor");
                    se2t.Espera();
                    se2.Libera();
                    System.out.println("La linea " + linea + " esta instalando la transimision");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if (!Linea.getEst()[linea][estacion + 1].isAlive()) {
                        Linea.getEst()[linea][estacion + 1].start();
                        Linea.getEst()[linea][estacion + 1].work.Libera();
                    } else
                        Linea.getEst()[linea][estacion + 1].work.Libera();

                    se2t.Libera();
                    break;


            }
        }
        }
}
