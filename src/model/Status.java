package model;

import java.awt.*;

public class Status extends Canvas {
    String url = "spot.png";

    public Status(String url){
        this.url = url;
    }

    public Status(){

    }

    public void paint(Graphics g){

        g.drawImage(Rutinas.AjustarImagen(url,getWidth(),getHeight()).getImage(),0,0,null);


    }
}
