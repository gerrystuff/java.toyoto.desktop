package TESTING.letseeing;

import java.text.DecimalFormat;

public class Testing {
    static double[] numeros;
    public static void main(String[] args){
        numeros = new double[50];

        DecimalFormat df = new DecimalFormat("#.0000");

        double vector [] = {0.27304,
                0.39473,
                0.39628,
                0.29484,
                0.16280,
                0.22728,
                0.70362,
                0.44932,
                0.51817,
                0.16891,
                0.90081,
                0.08451,
                0.81072,
                0.68700,
                0.25325,
                0.73977,
                0.56526,
                0.05642,
                0.04333,
                0.33879,
                0.40352,
                0.55326,
                0.66381,
                0.13626,
                0.66381,
                0.13626,
                0.25736,
                0.39503,
                0.34643,
                0.36279,
                0.25769,
                0.27440,
                0.80998,
                0.83761,
                0.68107,
                0.81129,
                0.25316,
                0.68670,
                0.99344,
                0.75680,
                0.04076,
                0.70353,
                0.46874,
                0.32257,
                0.63565,
                0.82029,
                0.07998,
                0.58045,
                0.81450,
                0.61916,
                0.36129,
                0.47976,

        };
        ordenar(vector);

        double vector2 [] = new double[50];

        double vector3 [] = new double[50];
        System.out.println("\nOrdenados\n");


        for(int i = 0; i <50; i++)
            vector2[i] =(double) (i+1) / 50;

            for(int i = 0; i <50; i++) {
                vector3[i] = Math.abs(numeros[i] - vector2[i]);
            }


            ordenar(vector3);
        for(int i = 0; i <50; i++) {
            System.out.println(df.format(vector3[i]));
        }



        double x = Math.abs(0.04076 - 0.0200);
        System.out.println(x);
    }

    static void ordenar(double matriz[]){
        double aux;
        for(int i = 0; i<matriz.length; i++){
            for(int j = 1; j<(matriz.length-i); j++){
                if(matriz[j-1] > matriz[j]){
                    aux = matriz[j-1];
                    matriz[j-1] = matriz[j];
                    matriz[j] = aux;
                }
            }
        }
    }
}
