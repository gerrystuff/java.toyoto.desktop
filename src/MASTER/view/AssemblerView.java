package MASTER.view;

import MASTER.controller.AssemblerController;
import MASTER.model.AssemblerModel;
import MASTER.model.Line;
import MASTER.model.Station;

import javax.swing.*;
import java.awt.*;

public class AssemblerView extends JFrame {

   private AssemblerModel model;
   private AssemblerController controller;


    private Line lines[];
    private Station stations[];
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

        this.setLayout(new GridLayout(0,6));

        for(int i = 0; i < 6 ; i++) {
            stations[i] = new Station(i + 1);
            add(stations[i]);
        }


        for(int i = 0; i < lines.length; i++){
            lines[i] = new Line();
            for(int k = 0; k < 6 ; k++)
            add(lines[i].getStatus(k));
        }

        setVisible(true);
    }

    public void setData(int linequant){
        lines = new Line[linequant];
        stations = new Station[6];
    }

    public static void main(String[] args) {
        new AssemblerView();

    }
}