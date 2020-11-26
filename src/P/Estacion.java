package P;

import HILOS.Semaforo;
import com.sun.security.jgss.GSSUtil;

public class Estacion extends Thread{
        int linea,estacion;
        static Estacion [][] mEst;
        Semaforo ss;
        boolean f = false;
        static Semaforo se1 = null;
        static Semaforo se2,se2t,se3,se4,se5,se6,s;
        static int contProduccion;
        private char tipo;

        public int getLinea(){
            return linea;
        }

        public int getEstacion(){
            return estacion;
        }

        public Estacion(char tipo,int linea, int estacion){
            this.tipo = tipo;
            this.linea = linea;
            this.estacion = estacion;
            if(tipo == 'C')
                this.ss = new Semaforo(1);
            else
                this.ss = new Semaforo(0);

            if(se1 == null){
                se1 = new Semaforo(5);
                se2= new Semaforo(4);
                se2t = new Semaforo(2);
                se3 = new Semaforo(3);
                se4 = new Semaforo(3);
                se5 = new Semaforo(2);
                se6 = new Semaforo(5);
                s = new Semaforo(1);

                contProduccion = 0;


            }

        }

        public void run(){
            System.out.println(getName()+ "Inicia linea de produccion");
            while (true){
                switch (tipo) {
                    case 'C':
                        Linea.getEst()[linea][estacion].ss.Espera();
                        se1.Espera();
                        s.Espera();
                        if (contProduccion == 25) {
                            Linea.getEst()[linea][estacion + 1].f = true;
                            Linea.getEst()[linea][1].ss.Libera();

                            s.Libera();
                            se1.Libera();
                            Linea.getEst()[linea][0].stop();
                        }
                        contProduccion++;

                        System.out.println(linea + "Frabicando el carro #" + contProduccion);
                        s.Libera();

                        System.out.println("La linea " + linea + "esta instalando el chasis");
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (Linea.getEst()[linea][estacion + 1].isAlive()) {
                            Linea.getEst()[linea][estacion + 1].start();
                            Linea.getEst()[linea][estacion + 1].ss.Libera();
                        } else
                            Linea.getEst()[linea][estacion + 1].ss.Libera();

                        se1.Libera();
                        break;

                    case 'R':

                        Linea.getEst()[linea][estacion].ss.Espera();
                        if (Linea.getEst()[linea][estacion].f && !Linea.getEst()[linea][estacion - 1].isAlive()) {
                            Linea.getEst()[linea][estacion + 1].f = true;
                            Linea.getEst()[linea][estacion + 1].ss.Libera();

                            Linea.getEst()[linea][estacion].stop();
                        }

                        se2.Espera();
                        Linea.getEst()[linea][estacion - 1].ss.Libera();
                        System.out.println("La linea " + linea + " esta instalando el motor");
                        se2t.Espera();
                        se2.Libera();
                        System.out.println("La linea " + linea + " esta instalando la transimision");
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        if (Linea.getEst()[linea][estacion + 1].isAlive()) {
                            Linea.getEst()[linea][estacion + 1].start();
                            Linea.getEst()[linea][estacion + 1].ss.Libera();
                        } else
                            Linea.getEst()[linea][estacion + 1].ss.Libera();

                        se2t.Libera();
                        break;



                }
            }
        }
}
