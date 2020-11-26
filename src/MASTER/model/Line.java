package MASTER.model;

import java.util.Arrays;

public class Line  {
  private  boolean station[];
  public Status status [];
  public int spot;

    public Line(){
        station = new boolean[6];
        Arrays.fill(station,false);
        spot = 0;

        status = new Status[6];

        for( int i = 0; i<status.length; i++)
        status[i] = new Status();
    }

    public Status getStatus(int i){
        return status[i];
    }


    /*

    @startuml
    class Line{
    }
    @enduml

     */
}
