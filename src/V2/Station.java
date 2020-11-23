package V2;

public class Station {
    public int Robots;
    public static int cantAutomoviles = 0;

    public Station(int canRobots){
        this.Robots =canRobots;
    }

    public synchronized void waitingRobot(String name){
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

    public synchronized void freeRobot(String name){
        System.out.println(name + ": Termino el proceso, robot disponible. Siguiente estacion -->");
        Robots++;
        notifyAll();
    }

    public void otroVehiculo(){

    }
}
