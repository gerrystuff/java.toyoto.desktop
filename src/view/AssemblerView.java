package view;

import controller.AssemblerController;
import model.AssemblerModel;
import model.Line;
import model.Station;

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
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new AssemblerModel();
        controller = new AssemblerController(this,model);

        setLayout(new GridLayout(lines.length,1));

        for(int i = 0; i < lines.length; i++){
            lines[i] = new Line();
            add(lines[i]);
        }

        setVisible(true);
    }

    public void setData(int linequant){
        lines = new Line[linequant];
        stations = new Station[6];

        for(int i = 0; i < stations.length; i++) {
            stations[i] = new Station(i + 1);
            System.out.println(stations[i].getName());
        }
    }

    public static void main(String[] args) {
        new AssemblerView();

    }
}
