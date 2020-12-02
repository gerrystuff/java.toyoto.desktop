package jv.model.stations;

import TESTING.MASTER.model.Rutinas;
import jv.vista.AssemblerView;

import java.awt.*;

import static java.awt.Color.BLUE;

public class StationChasis extends StationManager implements Runnable {


   public StationChasis(int pos, int line) {
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
           canWork.Espera(); //Semaforo personal que indica si la estacion puede trabajar. chasis empieza en verde.

           flag0.Espera();

           workingOn = AssemblerView.getNewcar();

           if (workingOn == -1)
           {
               end = true;
               repaint();
               flag0.Libera();
               break;
           }

           flag0.Libera();
           robotOn = false;
           repaint();

           semaforos[0].Espera();

           try {
               veh ="Vehiculo: " + workingOn;
               robotOn = true;
               repaint();
               Thread.sleep(4000);
           } catch (InterruptedException e) { }

           AssemblerView.stations[line][nextStation].workingOn = workingOn;
           AssemblerView.stations[line][nextStation].canWork.Libera();

       }
    }
}
