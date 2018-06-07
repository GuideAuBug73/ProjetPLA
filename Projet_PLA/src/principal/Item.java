package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;
import basic.Orientation;

public class Item extends IA {
    public int type;
    public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi
    boolean casted = false;
    public int orientation;
    public int limit;

    public Item(int type, int w, int h, BufferedImage m_item, Model model) {
        this.type = type;
        m_model = model;
        x = w;
        y = h;
        img = m_item;
        if (this.type == 0 || this.type == 1) {
            limit = 40;
        } else if (this.type == 2 || this.type == 3) {
            limit = 6;
        } else if (this.type == 4 || this.type == 5) {
            limit = 6;
        }
    }

    public Item(int type, Cellule cell, BufferedImage m_item, Model model) {
        this.type = type;
        m_model = model;
        x = cell.x;
        y = cell.y;
        img = m_item;
    }

    public void setcast(int x, int y, int orientation) {
        casted = true;
        possession = 3;
        this.orientation = orientation;
        this.x = x;
        this.y = y;

        if (orientation == 0) {
            this.y += 60;
        } else if (orientation == 1) {
            this.x += 60;
        } else if (orientation == 2) {
            this.x -= 60;
        } else if (orientation == 3) {
            this.y -= 60;
        }


    }


    public void paint(Graphics g) {
        if (possession == 0 || possession == 3)
            g.drawImage(img, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }

    public void paint(Graphics g, int w, int h) {
        Image img = this.img;
        if (possession == 1)
            g.drawImage(img, w, h, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }
}