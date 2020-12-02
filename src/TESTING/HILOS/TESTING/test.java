package TESTING.HILOS.TESTING;
;

class Estacion { private int Robots;

    public Estacion(int canRobots){
        this.Robots =canRobots;
    }

    public synchronized void Espera(String name){
        while (Robots <1) {
            try {
                System.out.println(name + ": Lo sentimos la cantidad de robots que estan disponibles son:  " + Robots +" .. te toca esperar.");
                wait();
                System.out.println(name + ": Checando si existen robots disponibles.");
            } catch (InterruptedException e) {
            }


        }
        System.out.println(name+ ": En hora buena exiten " + Robots +" robots disponibles .. robot asignado.");

        Robots--;

    }

    public synchronized void Libera(String name){
        System.out.println(name + ": Termino el proceso, robot disponible. Siguiente estacion -->");
        Robots++;
        notifyAll();
    }
}

class linea extends Thread {

    static Estacion se1;
    String name = "Linea #" + (getName().substring(getName().length() - 1, getName().length()));

    public linea() {
        se1 = new Estacion(3);
        setName(name);
    }

    public void run() {
        se1.Espera(getName());

        try {
         //   activo();
            System.out.println(getName() + ": Robot trabajando en esta linea");

            sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        se1.Libera(getName());
    }

    public void activo() {
        setName(name  + "+");
    }


    public void inactivo() {
        setName(name  + "-");
    }
}
public class test {

    public static void main(String[] args) {

        linea [] lin = new linea[5];

        for(int i = 0; i < 5; i++)
            lin[i] = new linea();

        for(int k = 0; k < 5; k++)
            lin[k].start();
    }
}
