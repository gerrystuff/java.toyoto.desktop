package TESTING.MASTER.view;

import TESTING.MASTER.controller.AssemblerController;
import TESTING.MASTER.model.AssemblerModel;
import TESTING.MASTER.model.Line;
import TESTING.MASTER.model.Station;

import javax.swing.*;

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
