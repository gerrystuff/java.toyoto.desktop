package P;

public class Linea {
    static Estacion[][] mEst;
    public Linea(Estacion [][] matriz){
        this.mEst = matriz;
        for(int i= 0; i < mEst.length;i++){
            mEst[i][0] = new Estacion('C',i,0);
            mEst[i][1] = new Estacion('M',i,1);
            mEst[i][2] = new Estacion('E',i,2);
            mEst[i][3] = new Estacion('I',i,3);
            mEst[i][4] = new Estacion('L',i,4);
            mEst[i][5] = new Estacion('P',i,5);

        }

        activaEstacion();
    }

    public void activaEstacion(){
        for(int i = 0; i < mEst.length; i++)
            mEst[i][0].start();
    }


    public static Estacion[][] getEst(){
        return mEst;
    }

    public static void main(String[] args) {
        Linea linea = new Linea(new Estacion[6][6]);
        linea.activaEstacion();
    }
}
