package V4.modelo.stations;

import MASTER.model.Rutinas;
import V4.vista.AssemblerView;

import java.awt.*;

import static java.awt.Color.BLUE;

public class StationTests extends StationController implements Runnable{
    public StationTests(int pos, int line) {
        super(pos, line);
    }

    @Override
    void Dibuja() {
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

            s5.Espera();

            AssemblerView.stations[line][previusStation].robotOn = false;
            AssemblerView.stations[line][previusStation].repaint();

            s4.Libera();

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

            s5.Libera();

    }
    }
}
