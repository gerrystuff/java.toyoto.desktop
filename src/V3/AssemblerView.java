package V3;

import MASTER.model.Rutinas;
import V3.controller.AssemblerController;
import V3.controller.StationController;
import V3.model.AssemblerModel;
import V3.model.Semaforo;

import javax.swing.*;
import java.awt.*;

public class AssemblerView extends JFrame {

    public static void main(String[] args) {
        new AssemblerView();
    }

    private AssemblerModel model;
    private AssemblerController controller;
    public static Stat stations[][];
    private JPanel centro;
    static JLabel label;

    public AssemblerView() {
        setTitle("Ensambladora de automoviles TOYOTO");
        doInterface();
    }

    private void doInterface() {
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        centro = new JPanel();
        centro.setLayout(new GridLayout(0, 6));

        model = new AssemblerModel();
        controller = new AssemblerController(this, model);

        JButton btn = new JButton("GO");
        btn.addActionListener(e -> go());

        label = new JLabel("0");

        add(label,BorderLayout.SOUTH);


        add(btn, BorderLayout.NORTH);

        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                centro.add(stations[i][k]);
        }

        add(centro);

        setVisible(true);


    }

    public void go() {
        for (int i = 0; i < stations[0].length; i++) {
            for (int k = 0; k < stations.length; k++)
                new Thread(stations[k][i]).start();
        }
    }

    public void setData(int linequant) {
        stations = new Stat[10][6];
        for (int i = 0; i < stations.length; i++) {
            for (int k = 0; k < stations[0].length; k++)
                stations[i][k] = new Stat(k, i);
        }

    }


    static class Stat extends StationController implements Runnable {

        public static Semaforo s1, s2, s2t, s3, s4, s5, s6, newCar,flag;
        public Semaforo canWork;
        static int vehiculos;
        int waiting =  4000;

        boolean apagada,robot,robot2 = false;
        Graphics g;
        Image buffer = null;


        public Stat(int pos, int line) {
            super(pos, line);
            if (s1 == null) {


                s1 = new Semaforo(5);
                s2 = new Semaforo(4);
                s2t = new Semaforo(2);
                s3 = new Semaforo(3);
                s4 = new Semaforo(3);
                s5 = new Semaforo(2);
                s6 = new Semaforo(5);
                newCar = new Semaforo(1);
                vehiculos = 0;
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

        String veh = "";

        @Override
        public void update(Graphics g) {
            paint(g);
        }


        public void Dibuja() {
            if(position == 1)
                g.drawImage(Rutinas.AjustarImagen("motor-transmision.png", getWidth(), getHeight()).getImage(), 0, 0, null);
            else
            g.drawImage(Rutinas.AjustarImagen("encendida.png", getWidth(), getHeight()).getImage(), 0, 0, null);

            g.drawString(veh,50,10);

            if(robot)
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 50, 35, null);

            if(robot2)
            g.drawImage(Rutinas.AjustarImagen("robot2.png", 40, 40).getImage(), 120, 35, null);


        }

        @Override
        public void run() {

            while (true) {
                switch (position) {
                    case 0:
                        canWork.Espera(); //Semaforo personal que indica si la estacion puede trabajar. chasis empieza en verde.
                        robot = false;
                        repaint();
                        s1.Espera();
                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s1.Libera();
                        stations[line][position + 1].canWork.Libera();

                        break;
                    case 1:
                        canWork.Espera();

                        s2.Espera();

                        stations[line][position-1].canWork.Libera();


                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(1800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        s2t.Espera();
                        robot2 = true;
                        robot = false;
                        repaint();

                        s2.Libera();

                        try {
                            Thread.sleep(1300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot2 = false;
                        repaint();
                        s2t.Libera();
                        stations[line][position + 1].canWork.Libera();
                        break;

                    case 2:
                        canWork.Espera();

                        s3.Espera();
                        stations[line][position-1].canWork.Libera();

                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s3.Libera();
                        stations[line][position + 1].canWork.Libera();
                        break;

                    case 3:
                        canWork.Espera();
                        s4.Espera();
                        stations[line][position-1].canWork.Libera();

                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s4.Libera();
                        stations[line][position + 1].canWork.Libera();
                        break;

                    case 4:
                        canWork.Espera();
                        s5.Espera();
                        stations[line][position-1].canWork.Libera();

                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s5.Libera();
                        stations[line][position + 1].canWork.Libera();
                        break;

                    case 5:
                        canWork.Espera();
                        s6.Espera();
                        stations[line][position-1].canWork.Libera();
                        try {
                            robot = true;
                            repaint();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s6.Libera();
                        canWork.Libera();
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


