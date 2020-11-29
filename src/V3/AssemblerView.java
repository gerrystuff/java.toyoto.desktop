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
        setSize(1200, 500);
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
        stations = new Stat[5][6];
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

        @Override
        public void update(Graphics g) {
            paint(g);
        }


        public void Dibuja() {
            g.drawImage(Rutinas.AjustarImagen("encendida.png", getWidth(), getHeight()).getImage(), 0, 0, null);

            if(robot)
            g.drawImage(Rutinas.AjustarImagen("robot.png", 40, 40).getImage(), 100, 35, null);

            if(robot2)
            g.drawImage(Rutinas.AjustarImagen("robot2.png", 40, 40).getImage(), 100, 35, null);

        }

        @Override
        public void run() {

            while (true) {

                switch (position) {
                    case 0:

                        canWork.Espera(); //Semaforo personal que indica si la estacion puede trabajar. chasis empieza en verde.
                        s1.Espera();
                        newCar.Espera(); //Semaforo global que indica si se puede ensamblar un carro nuevo

                        if (vehiculos == 20) {
                            apagada = true;
                            repaint();
                            canWork.Libera();

                            newCar.Libera();
                            s1.Libera();
                            new Thread(stations[line][0]).stop();
                        }


                      //  flag.Espera();
                       // System.out.println(getName() + "entre");
                        vehiculos++;
                        label.setText(String.valueOf(vehiculos));
                        System.out.println(getName() + " fabricando vehiculo #" + vehiculos);
                    //    flag.Libera();


                        newCar.Libera();

                        try {
                            robot = true;
                            repaint();
                            System.out.println(getName() + " esta instalando el chasis");
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();


                      /*  if (!stations[line][position + 1].estaVivo()) {
                            System.out.println("Lo arranco..");
                            (stations[line][position + 1]).run();//arranca siguiente linea

                        }
*/
  //                      System.out.println("Prosigo..");
                        stations[line][position + 1].canWork.Libera(); // la siguinte linea libera robot

                        s1.Libera();

                        break;


                    case 1:

                        System.out.println("INICIA ESTACION 2 ");
                        canWork.Espera();
                        System.out.println("Entre");
                        if (apagada && !stations[line][position - 1].estaVivo()) { //checamos si aun se pueden hacer carros.
                            System.out.println("PeEPE");
                            stations[line][position + 1].apagada = true;
                            repaint();
                            stations[line][position + 1].canWork.Libera();

                            stop();

                        }

                        s2.Espera();

                        stations[line][position - 1].canWork.Libera(); //puede entrar otro vehiculo ala estacion anterior
                        try {
                            System.out.println("xxxxxxx");
                            robot = true;
                            repaint();
                            System.out.println(getName() +" esta instalando el motor" + waiting);
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        robot = false;
                        repaint();

                        s2t.Espera(); //incia la instalacion de la transmision

                        s2.Libera(); //liberamos la instalacion del motor

                        try {
                            System.out.println(getName()+" esta instalando la transmision");
                            robot2 = true;
                            repaint();

                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        robot2 = false;
                        repaint();

                      //  if (!stations[line][position + 1].estaVivo())
                        //   new Thread(stations[line][position + 1]).start(); //arranca siguiente linea

                        stations[line][position + 1].canWork.Libera(); // la siguinte linea libera robot

                        s2t.Libera();
                        break;

                    case 2:
                        canWork.Espera();

                        if (apagada && !stations[line][position-1].estaVivo()) { //checamos si aun se pueden hacer carros.
                            stations[line][position + 1].apagada = true;
                            repaint();
                            stations[line][position + 1].canWork.Libera();

                            stop();

                        }

                        apagada = true;
                        s3.Espera();
                        apagada = false;
                        stations[line][position-1].canWork.Libera(); //puede entrar otro vehiculo ala estacion anterior

                        try {

                            robot = true;
                            repaint();
                            System.out.println(getName() + " esta instalando la carroceria");
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();

                 //       if (!stations[line][position + 1].estaVivo())
                   //         new Thread(stations[line][position + 1]).start(); //arranca siguiente linea

                        stations[line][position + 1].canWork.Libera(); // la siguinte linea libera robot

                        s3.Libera();
                        break;

                    case 3:
                        canWork.Espera();

                        if (apagada && !stations[line][position-1].estaVivo()) { //checamos si aun se pueden hacer carros.
                            stations[line][position + 1].apagada = true;
                            repaint();
                            stations[line][position + 1].canWork.Libera();

                            stop();

                        }

                        s4.Espera();

                        stations[line][position-1].canWork.Libera(); //puede entrar otro vehiculo ala estacion anterior

                        try {
                            robot = true;
                            repaint();
                            System.out.println(getName() +" esta instalando los interiores");
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();

                     //   if (!stations[line][position + 1].estaVivo())
                       //     new Thread(stations[line][position + 1]).start(); //arranca siguiente linea

                        stations[line][position + 1].canWork.Libera(); // la siguinte linea libera robot

                        s4.Libera();
                        break;

                    case 4:
                        canWork.Espera();

                        if (apagada && !stations[line][position-1].estaVivo()) { //checamos si aun se pueden hacer carros.
                            stations[line][position + 1].apagada = true;
                            repaint();
                            stations[line][position + 1].canWork.Libera();

                            stop();

                        }

                        s5.Espera();

                        stations[line][position-1].canWork.Libera(); //puede entrar otro vehiculo ala estacion anterior

                        try {
                            robot = true;
                            repaint();
                            System.out.println(getName() +" esta instalando la carroceria");
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                            robot = false;
                            repaint();
                       // if (!stations[line][position + 1].estaVivo())
                         //   new Thread(stations[line][position + 1]).start(); //arranca siguiente linea


                        stations[line][position + 1].canWork.Libera(); // la siguinte linea libera robot

                        s5.Libera();
                        break;

                    case 5:
                        canWork.Espera();

                        if (apagada && !stations[line][position-1].estaVivo())  //checamos si aun se pueden hacer carros.
                            stop();

                        s6.Espera();

                        stations[line][position-1].canWork.Libera(); //puede entrar otro vehiculo ala estacion anterior

                        try {
                            robot = true;
                            repaint();

                            System.out.println(getName()+ " esta haciendo las pruebas");
                            Thread.sleep(waiting);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        robot = false;
                        repaint();
                        s6.Libera();
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
//    }
//
//            switch (position) {
//                case 1:
//                    try {
//                        System.out.println("Trabajando en el chasis");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    working = false;
//                    repaint();
//                    break;
//
//                case 2:
//                    try {
//                        System.out.println("Trabajando en el motor");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    working = false;
//                    repaint();
//                    break;
//
//                case 3:
//                    try {
//                        System.out.println("Trabajando en el carroceria");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    working = false;
//                    repaint();
//                    break;
//                case 4:
//                    try {
//                        System.out.println("Trabajando en las llantas");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    working = false;
//                    repaint();
//                    break;
//                case 5:
//                    s5.Espera();
//                    s4.Libera();
//                    try {
//                        System.out.println("Trabajando en el interiores");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    working = false;
//                    repaint();
//                    break;
//                case 6:
//                    try {
//                        System.out.println("Trabajando en las pruebas ");
//                        working = true;
//                        repaint();
//                        Thread.sleep(3200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("ia mevoi..");
//                    working = false;
//                    repaint();
//                    break;
//
//            }
//            repaint();
//        }
    }
    }


