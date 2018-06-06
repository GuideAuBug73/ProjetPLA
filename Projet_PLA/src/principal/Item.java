package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    public int type;
    public int w;
    public int h;
    BufferedImage m_item;

    public Item(int type) {
        this.type = type;
    }
    public Item(int type,int w, int h,BufferedImage m_item){
        this.type=type;
        this.w=w;
        this.h=h;
        this.m_item=m_item;
    }


    public void paint(Graphics g) {
        Image img=m_item;
        g.drawImage(img,w,h,Options.TAILLE_CELLULE,Options.TAILLE_CELLULE, null);
    }

}
