package TESTING.V2;

public class Line extends Thread {

    static Station station1;
    String name = "Linea #" + (getName().substring(getName().length() - 1, getName().length()));

    public Line() {
        station1 = new Station(2);
        setName(name);
    }

    public void run() {
            station1.waitingRobot(getName());

            try {
                System.out.println(getName() + ": Robot trabajando.." + "\t Robots Osiciosos : " + station1.Robots);
                sleep(2500);

            } catch (InterruptedException e) {  e.printStackTrace(); }
            station1.freeRobot(getName());
        }
}

