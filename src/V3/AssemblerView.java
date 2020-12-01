package V3;

import MASTER.model.Rutinas;
import V3.controller.AssemblerController;
import V3.controller.StationController;
import V3.model.AssemblerModel;
import V3.model.Semaforo;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;

public class AssemblerView extends JFrame {

    public static void main(String[] args) {
        new AssemblerView();
    }

    private AssemblerModel model;
    private AssemblerController controller;
    public static Stat stations[][];
    private JPanel center,header;
    static JLabel contadorAutos,chasis;
    static JTextField jt;

    public AssemblerView() {
        setTitle("Ensambladora de automoviles TOYOTO");
        doInterface();
    }

    private void doInterface() {
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        center = new JPanel();
        center.setLayout(new GridLayout(0, 6));

        model = new AssemblerModel();
        controller = new AssemblerController(this, model);

        JButton btn = new JButton();
        btn.setFont(new Font("Tahoma",1,26));
        btn.setText("EMPEZAR PRODUCCION");
        btn.addActionListener(e -> go());

        btn.setPreferredSize(new Dimension(550,100));


        contadorAutos = new JLabel();
        header = new JPanel();

        header.add(btn);


        contadorAutos.setBackground(WHITE);
        contadorAutos.setPreferredSize(new Dimension(getWidth()/2,100));
        contadorAutos.setFont(new Font("Tahoma",1,26));

        contadorAutos.setVerticalAlignment(SwingConstants.CENTER);
        contadorAutos.setText("        VEHICULOS TERMINADOS : ");
        header.add(contadorAutos);

        add(header, BorderLayout.NORTH);



        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                center.add(stations[i][k]);
        }

        add(center);

        setVisible(true);


    }

    public void go() {
        for (int i = 0; i < stations[0].length; i++) {
            for (int k = 0; k < stations.length; k++)
                new Thread(stations[k][i]).start();
        }
    }

    public void setData(int linequant) {
        stations = new Stat[10 ][6];
        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                stations[i][k] = new Stat(k, i);
        }

    }


    static class Stat extends StationController implements Runnable {

        public static Semaforo s0, s1, s1t, s2, s3, s4, s5, newCar, flag0,flag1;
        public Semaforo canWork;
        static int vehiculosEnProceso,vehiculosTerminados;
        String veh = "";
        int vh ,vh2;

        boolean stopWorking,robot,robot2 = false;
        Graphics g;
        Image buffer = null;


        public Stat(int pos, int line) {
            super(pos, line);
            if (s0 == null) {


                s0 = new Semaforo(5);
                s1 = new Semaforo(4);
                s1t = new Semaforo(2);
                s2 = new Semaforo(3);
                s3 = new Semaforo(3);
                s4 = new Semaforo(2);
                s5 = new Semaforo(5);
                newCar = new Semaforo(1);

                flag0 = new Semaforo(1);
                flag1 = new Semaforo(1);

                vehiculosEnProceso = 0;
            }

            if (position == 0)
                canWork = new Semaforo(1);
            else
                canWork = new Semaforo(0);

        }

        public void paint(Graphics g) {
            if (buffer == null) {
                buffer = createImage(getWidth(), getHeight());
                this.g = buffer.getGraphics();
                repaint();
                return;
            }


            Dibuja();
            g.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);


        }


        @Override
        public void update(Graphics g) {
            paint(g);
        }


        public void Dibuja() {
            if (stopWorking) {
                g.drawImage(Rutinas.AjustarImagen("apagada.png", getWidth(), getHeight()).getImage(), 0, 0, null);
                return;
            }
            if (position == 1)
                g.drawImage(Rutinas.AjustarImagen("motor-transmision.png", getWidth(), getHeight()).getImage(), 0, 0, null);
            else
                g.drawImage(Rutinas.AjustarImagen("encendida.png", getWidth(), getHeight()).getImage(), 0, 0, null);

            g.setFont(new Font("Tahoma", 3, 12));

            if (robot) {
                g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 50, 35, null);
                g.setColor(BLUE);
                g.drawString(veh, 10, 20);
            }
            if (robot2) {
                g.drawImage(Rutinas.AjustarImagen("robot2.png", 40, 40).getImage(), 120, 35, null);
                g.setColor(WHITE);
                g.drawString(veh, 115, 20);

            }
        }

        @Override
        public void run() {

            while (true) {
                switch (position) {
                    case 0:
                        canWork.Espera(); //Semaforo personal que indica si la estacion puede trabajar. chasis empieza en verde.

                        flag0.Espera();
                        vehiculosEnProceso++;

                        if(vehiculosEnProceso > 20){
                            System.out.println("ESTACION MUERTA.");
                            flag0.Libera();
                            break;
                        }

                        vh = vehiculosEnProceso;

                        flag0.Libera();


                        robot = false;
                        repaint();

                        s0.Espera();

                        try {
                            veh ="Vehiculo: " + vh;
                            robot = true;
                            repaint();
                            Thread.sleep(4000);
                        } catch (InterruptedException e) { }

                        stations[line][1].vh = vh;
                        stations[line][1].canWork.Libera();

                        break;
                    case 1:
                        canWork.Espera();

                        s1.Espera();


                        stations[line][0].robot = false;
                        stations[line][0].repaint();
                        stations[line][0].canWork.Libera();

                        s0.Libera();


                        try {
                            veh = "Vehiculo: "+vh;
                            robot = true;
                            repaint();
                            Thread.sleep(1800);
                        } catch (InterruptedException e) { }

                        vh2 = vh;
                        s1t.Espera();

                        veh = "Vehiculo: " +vh2;
                        robot2 = true;
                        robot = false;
                        repaint();

                        s1.Libera();

                        try {

                            Thread.sleep(1400);
                        } catch (InterruptedException e) { }



                        stations[line][2].vh = vh2;
                        stations[line][2].canWork.Libera();

                        break;

                    case 2:
                        canWork.Espera();

                        s2.Espera();

                        stations[line][1].robot2 = false;
                        stations[line][1].repaint();

                        s1t.Libera();
;
                        try {
                            veh = "Vehiculo: "+vh;
                            robot = true;
                            repaint();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) { }

                        stations[line][3].vh = vh;
                        stations[line][3].canWork.Libera();
                        break;

                    case 3:
                        canWork.Espera();

                        s3.Espera();

                        stations[line][2].robot = false;
                        stations[line][2].repaint();

                        s2.Libera();

                        try {
                            veh = "Vehiculo: "+vh;
                            robot = true;
                            repaint();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) { }


                        stations[line][4].vh = vh;
                        stations[line][4].canWork.Libera();
                        break;

                    case 4:
                        canWork.Espera();

                        s4.Espera();

                        stations[line][3].robot = false;
                        stations[line][3].repaint();

                        s3.Libera();

                        try {
                            veh = "Vehiculo: "+vh;
                            robot = true;
                            repaint();
                            Thread.sleep(1500);
                        } catch (InterruptedException e) { }


                        stations[line][5].vh = vh;
                        stations[line][5].canWork.Libera();
                        break;

                    case 5:
                        canWork.Espera();

                        s5.Espera();

                        stations[line][4].robot = false;
                        stations[line][4].repaint();

                        s4.Libera();

                        try {
                            veh = "Vehiculo: "+vh;
                            robot = true;
                            repaint();
                            Thread.sleep(4000);
                        } catch (InterruptedException e) { }

                        robot = false;
                        repaint();

                        flag1.Espera();
                        vehiculosTerminados++;
                        System.out.println(vehiculosTerminados);

                        contadorAutos.setText("        VEHICULOS TERMINADOS : " + vehiculosTerminados);


                        flag1.Libera();
                        s5.Libera();
                        break;

                }


            }
        }

        public boolean estaVivo() {
            Thread t = new Thread(this);
            return t.isAlive();
        }

        public void stop(){
            Thread t = new Thread(this);
            t.stop();
        }

    }
    }


