package jv.model;

import TESTING.MASTER.model.Rutinas;

import java.awt.*;

public class Lienzo extends Canvas {

    String image;
    Graphics g;
    Image buffer = null;

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

    public Lienzo(String image){
        this.image = image;
    }

    private void Dibuja(){
        g.drawImage(Rutinas.AjustarImagen(image,getWidth(),getHeight()).getImage(),0,0,null);
    };


    public void update(Graphics g){
        paint(g);
    }
}
