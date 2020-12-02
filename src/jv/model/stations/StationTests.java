package jv.model.stations;

import TESTING.MASTER.model.Rutinas;
import jv.vista.AssemblerView;

import java.awt.*;

import static java.awt.Color.BLUE;

public class StationTests extends StationManager implements Runnable{
    public StationTests(int pos, int line) {
        super(pos, line);
    }

    @Override
    void Dibuja() {
        if(end) {
            g.drawImage(Rutinas.AjustarImagen("apagada.png", getWidth(), getHeight()).getImage(), 0, 0, null);
            return;
        }
        else
            g.drawImage(Rutinas.AjustarImagen("encendida.png", getWidth(), getHeight()).getImage(), 0, 0, null);
        g.setFont(new Font("Tahoma", 3, 12));

        if(robotOn){
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 50, 35, null);
            g.setColor(BLUE);
            g.drawString(veh, 10, 20);
        }
    }

    @Override
    public void run() {
        while(true){
            canWork.Espera();

            semaforos[6].Espera();

            AssemblerView.stations[line][previusStation].robotOn = false;
            AssemblerView.stations[line][previusStation].repaint();

            semaforos[5].Libera();

            try {
                veh = "Vehiculo: "+ workingOn;
                robotOn = true;
                repaint();
                Thread.sleep(2000);
            } catch (InterruptedException e) { }

            robotOn = false;
            repaint();

            flag1.Espera();
            AssemblerView.setCompleteCar();
            flag1.Libera();

            semaforos[6].Libera();
            if(AssemblerView.stations[line][previusStation].end){
                end = true;
                repaint();
                break;
            }

    }
    }
}
