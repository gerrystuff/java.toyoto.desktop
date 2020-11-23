package V2.view;



import V2.Line;
import V2.Station;
import V2.controller.AssemblerController;
import V2.model.AssemblerModel;

import javax.swing.*;
import java.awt.*;

public class AssemblerView extends JFrame {
    private AssemblerModel model;
    private AssemblerController controller;

    public int cantAutomoviles = 0;


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

}
