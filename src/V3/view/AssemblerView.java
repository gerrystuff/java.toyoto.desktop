package V3.view;


import V3.Station;
import V3.controller.AssemblerController;
import V3.model.AssemblerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssemblerView extends JFrame {

   private AssemblerModel model;
   private AssemblerController controller;
   public Station stations[][];
   private JPanel centro;

    public AssemblerView(){
        setTitle("Ensambladora de automoviles TOYOTO");
        doInterface();
    }
    private void doInterface(){
        setSize(1200,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        centro = new JPanel();
        centro.setLayout(new GridLayout(0,6));

        model = new AssemblerModel();
        controller = new AssemblerController(this,model);

        JButton btn = new JButton("GO");
        btn.addActionListener(e -> go());

        add(btn,BorderLayout.NORTH);

        for(int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++){
                centro.add(stations[i][k]);
            }
        }

        add(centro);

        setVisible(true);


    }

    public void go(){
        for(int i = 0; i < stations[0].length; i++) {
            for (int k = 0; k < stations.length; k++){
                new Thread(stations[k][i]).start();
            }
        }
    }

    public void setData(int linequant){
        System.out.println(linequant);
        stations = new Station[7][6];
        for(int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
              stations[i][k] = new Station(k+1);
        }

    }

    public static void main(String[] args) {
        new AssemblerView();


    }
}
