package HILOS.TESTING.CARRERA;

import HILOS.Semaforo;

public class Puente {

    int inicio,fin;
    Semaforo semaforo;

    Puente(int inicio,int fin){
        semaforo = new Semaforo(1);
        this.inicio = inicio;
        this.fin = fin;
    }

    public int checapuente(int km,boolean tienePuente) {
        if (km >= inicio && km <= fin && !tienePuente) {
            semaforo.Espera();
            return 2;
        }

        if (km > fin && tienePuente) {
            semaforo.Libera();
            return 1;
        }

        return 0;
    }
}
