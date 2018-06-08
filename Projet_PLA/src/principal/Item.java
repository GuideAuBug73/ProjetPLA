package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;
import basic.Orientation;

public class Item extends IA {
    public int type;
    public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi
    public boolean casted = false;
    public int orientation;
    public int limit;
    public int compteurTickItem = 500;


    public Item(int type, int w, int h, BufferedImage m_item, Model model) {
        this.type = type;
        m_model = model;
        x = w;
        y = h;
        img = m_item;
        if (this.type == 0 || this.type == 1) {
            limit = 40 * 30;
        } else if (this.type == 2 || this.type == 3) {
            limit = 6 * 30;
        } else if (this.type == 4 || this.type == 5) {
            limit = 6 * 30;
        } else if (this.type == 13) {
            limit = 3 * 30;
        } else {
            limit = 0;
        }
    }

    public void setcast(int x, int y, int orientation) {
        casted = true;
        possession = 3;
        this.orientation = orientation;
        this.x = x;
        this.y = y;

        if (orientation == 0) {
            this.y += 2;
        } else if (orientation == 1) {
            this.x += 2;
        } else if (orientation == 2) {
            this.x -= 2;
        } else if (orientation == 3) {
            this.y -= 2;
        }
    }

    public void lanceItem() {
        if (compteurTickItem < 1) {
            compteurTickItem++;
        } else {
            verifCellule();
            if (Options.itemlance.limit != 0) {
                Options.itemlance.setcast(Options.itemlance.x, Options.itemlance.y, Options.itemlance.orientation);
                compteurTickItem = 0;
                Options.itemlance.limit--;
            } else {
                Options.itemlance.x = -100;
                Options.itemlance.y = -100;
                Options.itemlance = null;
                m_model.m_perso.projectile = new Item(13, -200,-200, m_model.m_spellSprite, m_model);

            }
        }

    }

    public void verifCellule() {
        int w = this.x / 60;
        int h = this.y / 60;
        try {
            if (m_model.m_carte.cellules[h][w].libre == false) {
                this.limit = 0;
            }
            if (m_model.m_carte.cellules[h][w].entité instanceof Ennemi) {
                System.out.println("Touche");
            }
        }catch (Exception e){

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