package model;

import java.util.Arrays;

public class Line {
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
}
