package MASTER.model;

import java.util.Random;

public class AssemblerModel {

  private Station stations[];

  public int getLines(){
      return new Random().nextInt(8) + 8;
  }
  public AssemblerModel(){


    }

    public void start(){
    }

    public static void main(String[] args) {
        new AssemblerModel();
    }


}
