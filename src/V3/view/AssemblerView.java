package V3.view;


import V3.Station;
import V3.controller.AssemblerController;
import V3.model.AssemblerModel;
import net.sourceforge.plantuml.bpm.Grid;

import javax.swing.*;
import java.awt.*;

public class AssemblerView extends JFrame {

   private AssemblerModel model;
   private AssemblerController controller;
   public Station stations[][];

    public AssemblerView(){
        setTitle("Ensambladora de automoviles TOYOTO");
        doInterface();
    }
    private void doInterface(){
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new AssemblerModel();
        controller = new AssemblerController(this,model);

        setLayout(new GridLayout(0,6));


        for(int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++){
                add(stations[i][k]);
            }
        }

        setVisible(true);
        for(int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++){
                System.out.println(k + " " + i);
               stations[k][i].run();
            }
        }


    }

    public void setData(int linequant){
        stations = new Station[2][6];
        for(int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
              stations[i][k] = new Station(k+1);
        }

        System.out.println(stations[0].length * stations.length);
    }

    public static void main(String[] args) {
        new AssemblerView();


    }
}
