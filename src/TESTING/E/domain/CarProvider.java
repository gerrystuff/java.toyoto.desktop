package TESTING.E.domain;

import java.util.Optional;

public class CarProvider {
    private final int TOTAL_CARS;
    private int numbersOfCarsProduced;
    private int numbersOfCarsRemaining;
    private static CarProvider manager;
    private final ShutdownManager shutdownManager;

    public CarProvider(int TOTAL_CARS, ShutdownManager shutdownManager) {

        this.TOTAL_CARS = TOTAL_CARS;
        this.numbersOfCarsRemaining = TOTAL_CARS;
        this.numbersOfCarsProduced = 0;
        this.shutdownManager = shutdownManager;
    }

    public static CarProvider getInstance(int totalCars, ShutdownManager shutdownManager) {
        if (manager == null)
            manager = new CarProvider(totalCars, shutdownManager);

        return manager;
    }

    public static CarProvider getInstance() {
        return getInstance(0, null);
    }

    public synchronized Optional<Car> getCar() {
        if (numbersOfCarsRemaining == 0)
            return Optional.empty();

        numbersOfCarsRemaining--;

        return Optional.of(new Car(TOTAL_CARS - numbersOfCarsRemaining));

    }

    public synchronized void addCarProduced(){
        numbersOfCarsProduced++;
        if(numbersOfCarsProduced == TOTAL_CARS)
            shutdown();
    }

    public synchronized int getNumbersOfCarsProduced(){return numbersOfCarsProduced;}

    public int numbersOfCarsToProduce(){ return TOTAL_CARS;}
    private void shutdown() {
        shutdownManager.shutdown();
    }


}
