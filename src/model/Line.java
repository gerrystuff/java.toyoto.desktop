package model;

import java.awt.*;
import java.util.Arrays;

public class Line  {
  private  boolean station[];
  public Status status [];
  public int spot;

    public Line(){
        station = new boolean[6];
        Arrays.fill(station,false);
        spot = 0;

        for( int i = 0; i<status.length; i++)
        status[i] = new Status();
    }

    public Status getStatus(int i){
        return status[i];
    }
    public void go(){
       station[spot] = true;
    }

    public void stop(){
        station[spot] = false;
        spot++;
    }

}
