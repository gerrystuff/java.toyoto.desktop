package view;

import controller.AssemblerController;
import model.AssemblerModel;
import model.Line;
import model.Station;
import model.Status;

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

        this.setLayout(new GridLayout(2,6));
        System.out.println(lines.length);
        stations[0]  = new Station(1);
        stations[1]  = new Station(2);

        add(stations[0]);
        add(stations[1]);

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
