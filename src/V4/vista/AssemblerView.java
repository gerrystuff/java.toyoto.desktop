package V4.vista;


import V4.modelo.stations.*;

import javax.swing.*;
import java.awt.*;

public class AssemblerView extends JFrame {

    public static void main(String[] args) {
        new AssemblerView();
    }
    static final int TOTALCARS = 20;
    static int cars;

    public static StationController stations[][];
    private JPanel center,header;
    static JLabel contadorAutos,chasis;
    static JTextField jt;

    public AssemblerView() {
        setTitle("Ensambladora de automoviles TOYOTO");
        cars = TOTALCARS;
        doInterface();
    }

    private void doInterface() {
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        center = new JPanel();
        center.setLayout(new GridLayout(0, 6));


        JButton btn = new JButton();
        btn.setFont(new Font("Tahoma",1,26));
        btn.setText("EMPEZAR PRODUCCION");
        btn.addActionListener(e -> go());

        btn.setPreferredSize(new Dimension(550,100));


        contadorAutos = new JLabel();
        header = new JPanel();

        header.add(btn);


        contadorAutos.setPreferredSize(new Dimension(getWidth()/2,100));
        contadorAutos.setFont(new Font("Tahoma",1,26));

        contadorAutos.setVerticalAlignment(SwingConstants.CENTER);
        contadorAutos.setText("        VEHICULOS TERMINADOS : ");
        header.add(contadorAutos);

        add(header, BorderLayout.NORTH);



        setData(10);

        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                center.add(stations[i][k]);
        }

        add(center);

        setVisible(true);


    }

    public static int getCars(){
        if(cars > 0) {
            cars--;
            return TOTALCARS - cars;
        }
        else
            return -1;
    }


    public void go() {
        for (int i = 0; i < stations[0].length; i++) {
            for (int k = 0; k < stations.length; k++)
                new Thread((Runnable) stations[k][i]).start();
        }
    }

    public void setData(int linequant) {
        stations = new StationController[linequant][6];
        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                switch (k) {
                    case 0: stations[i][k] = new StationChasis(k, i);
                            break;
                    case 1: stations[i][k] = new StationMotorTransmision(k,i);
                            break;
                    case 2: stations[i][k] = new StationBodyWork(k,i);
                            break;
                    case 3: stations[i][k] = new StationInside(k,i);
                            break;
                    case 4:stations[i][k] = new StationTires(k,i);
                            break;
                    case 5:stations[i][k] = new StationTests(k,i);
            }
            }

    }
    }


