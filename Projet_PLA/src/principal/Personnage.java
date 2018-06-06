
package principal;

import basic.Cellule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {
    Inventaire inventaire=new Inventaire();
    BufferedImage m_sprite;
    int m_w, m_h;
    int m_x, m_y;
    int m_idx;
    float m_scale;
    BufferedImage[] m_sprites;
    Model m_model;
    int orientation = 0;

    public Personnage(Model model, BufferedImage sprite, int x, int y, float scale) {
        m_model = model;
        m_sprite = sprite;
        m_x = x;
        m_y = y;
        m_scale = scale;
        m_idx = 0;
        splitSprite();
    }

    public void splitSprite() {
        int width = m_sprite.getWidth(null);
        int height = m_sprite.getHeight(null);
        m_sprites = new BufferedImage[4 * 4];
        m_w = width / 4;
        m_h = height / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = j * m_w;
                int y = i * m_h;

                m_sprites[(i * 4) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
            }
        }
    }

    public void droite() {
        if (m_x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
            Cellule cell = m_model.m_carte.cellules[m_y / Options.TAILLE_CELLULE][(m_x / Options.TAILLE_CELLULE) + 1];
            if (cell.libre) {
                ramasser(cell);
                m_x += Options.TAILLE_CELLULE;
                if (m_idx == 10)
                    m_idx = 11;
                else
                    m_idx = 10;
                this.orientation = 1;
            }
        }
    }

    public void haut() {
        if (m_y / Options.TAILLE_CELLULE != 0) {
            Cellule cell = m_model.m_carte.cellules[(m_y / Options.TAILLE_CELLULE) - 1][m_x / Options.TAILLE_CELLULE];
            if (cell.libre) {
                ramasser(cell);
                m_y -= Options.TAILLE_CELLULE;
                if (m_idx == 13)
                    m_idx = 14;
                else
                    m_idx = 13;
                this.orientation = 3;
            }
        }
    }

    public void bas() {
        if (m_y / Options.TAILLE_CELLULE != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE - 1)) {
            Cellule cell = m_model.m_carte.cellules[(m_y / Options.TAILLE_CELLULE) + 1][m_x / Options.TAILLE_CELLULE];
            // int x = cell.x ;
            // int y = cell.y ;
            if (cell.libre) {
                ramasser(cell);
                m_y += Options.TAILLE_CELLULE;
                if (m_idx == 2)
                    m_idx = 1;
                else
                    m_idx = 2;
                this.orientation = 0;
            }
        }
    }

    public void gauche() {
        if (m_x / Options.TAILLE_CELLULE != 0) {
            Cellule cell = m_model.m_carte.cellules[m_y / Options.TAILLE_CELLULE][(m_x / Options.TAILLE_CELLULE) - 1];

            if (cell.libre) {
                ramasser(cell);
                m_x -= Options.TAILLE_CELLULE;
                if (m_idx == 6)
                    m_idx = 5;
                else
                    m_idx = 6;
                this.orientation = 2;
            }
        }
    }


    public void ramasser(Cellule cell) {
        if(cell.entité!=null) {
            Item[] item = m_model.m_item;
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null) {
                    if (cell.x == item[i].w && cell.y == item[i].h) {
                        item[i].possession = 1;
                        inventaire.enfiler(item[i]);
                        cell.entité = null;
                    }
                }
            }
        }

    }
    public void paint(Graphics g) {

        Image img = m_sprites[m_idx];
        int w = (int) (m_scale * m_w);
        int h = (int) (m_scale * m_h);
        g.drawImage(img, m_x+10, m_y, w, h, null);

    }


}