package model;

import java.awt.*;
import java.util.Arrays;

public class Line extends Canvas {
  private  boolean station[];
  public int spot;

    public Line(){
        station = new boolean[6];
        Arrays.fill(station,false);
        spot = 0;
    }

    public void go(){
       station[spot] = true;
    }

    public void stop(){
        station[spot] = false;
        spot++;
    }

    public void paint(Graphics g){
        g.drawImage(Rutinas.AjustarImagen("cp8.png",getWidth(),getHeight()).getImage(),0,0,null);
    }
}
