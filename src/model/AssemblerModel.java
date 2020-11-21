package model;

import java.util.Random;

public class AssemblerModel {

  private Line lines[];
  private Station stations[];

  public AssemblerModel(){
        lines = new Line[new Random().nextInt(8)+8];
        stations = new Station[6];

        for(int i = 0; i < stations.length; i++) {
            stations[i] = new Station(i + 1);
            System.out.println(stations[i].getName());
        }

    }

    public void start(){
      for(Line element: lines)
          element.go();
    }



    public static void main(String[] args) {
        new AssemblerModel();
    }


}
