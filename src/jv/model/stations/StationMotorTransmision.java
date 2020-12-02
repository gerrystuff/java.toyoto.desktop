package jv.model.stations;

import TESTING.MASTER.model.Rutinas;
import jv.vista.AssemblerView;

import java.awt.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;

public class StationMotorTransmision extends StationManager implements Runnable {

    public int workingOn2;

    public StationMotorTransmision(int pos, int line) {
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
        if (robotOn) {
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 50, 35, null);
            g.setColor(BLUE);
            g.drawString(veh, 10, 20);
        }
        if (robotOn2) {
            g.drawImage(Rutinas.AjustarImagen("robot2.png", 40, 40).getImage(), 120, 35, null);
            g.setColor(WHITE);
            g.drawString(veh, 115, 20);

        }
    }

    @Override
    public void run() {
        while (true) {

            canWork.Espera();

            semaforos[1].Espera();

            AssemblerView.stations[line][previusStation].robotOn = false;
            AssemblerView.stations[line][previusStation].repaint();
            AssemblerView.stations[line][previusStation].canWork.Libera();

            semaforos[0].Libera();

            try {
                veh = "Vehiculo: " + workingOn;
                robotOn = true;
                repaint();
                Thread.sleep(1800);
            } catch (InterruptedException e) {
            }

            workingOn2 = workingOn;

            semaforos[2].Espera();

            veh = "Vehiculo: " + workingOn2;
            robotOn2 = true;
            robotOn = false;
            repaint();

            semaforos[1].Libera();

            try {

                Thread.sleep(1400);
            } catch (InterruptedException e) {
            }


            AssemblerView.stations[line][nextStation].workingOn = workingOn2;

            AssemblerView.stations[line][nextStation].canWork.Libera();

            if(AssemblerView.stations[line][previusStation].end){
                end = true;
                repaint();
                break;
            }


        }
    }
}
