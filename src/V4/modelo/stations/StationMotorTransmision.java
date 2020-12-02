package V4.modelo.stations;

import HILOS.Semaforo;
import MASTER.model.Rutinas;
import V2.Station;
import V4.vista.AssemblerView;

import java.awt.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;

public class StationMotorTransmision extends StationController implements Runnable {

    public int workingOn2;

    public StationMotorTransmision(int pos, int line) {
        super(pos, line);
    }

    @Override
    void Dibuja() {

        g.drawImage(Rutinas.AjustarImagen("motor-transmision.png", getWidth(), getHeight()).getImage(), 0, 0, null);
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

            s1.Espera();

            AssemblerView.stations[line][previusStation].robotOn = false;
            AssemblerView.stations[line][previusStation].repaint();
            AssemblerView.stations[line][previusStation].canWork.Libera();

            s0.Libera();

            try {
                veh = "Vehiculo: " + workingOn;
                robotOn = true;
                repaint();
                Thread.sleep(1800);
            } catch (InterruptedException e) {
            }

            workingOn2 = workingOn;

            s1t.Espera();

            veh = "Vehiculo: " + workingOn2;
            robotOn2 = true;
            robotOn = false;
            repaint();

            s1.Libera();

            try {

                Thread.sleep(1400);
            } catch (InterruptedException e) {
            }


            AssemblerView.stations[line][nextStation].workingOn = workingOn2;
            AssemblerView.stations[line][nextStation].canWork.Libera();


        }
    }
}
