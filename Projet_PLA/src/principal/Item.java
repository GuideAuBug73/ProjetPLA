package principal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    public int type;
    public int w;
    public int h;
    public int possession;//0 si non possédé 1 si c'est le joueur  2 si c'est l'ennemi
    BufferedImage m_item;


    public Item(int type, int w, int h, BufferedImage m_item) {
        this.type = type;
        this.w = w;
        this.h = h;
        this.m_item = m_item;
        possession = 0;
    }


    public void paint(Graphics g) {
        Image img = m_item;
        if (possession == 0)
            g.drawImage(img, w, h, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }
    public void paint(Graphics g,int w,int h) {
        Image img = m_item;
        if (possession == 1)
            g.drawImage(img, w, h, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }
}