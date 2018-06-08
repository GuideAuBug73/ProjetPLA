
package principal;

import basic.Cellule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {
	int m_w, m_h;
	int m_idx;
	float m_scale;
	BufferedImage[] m_sprites;
	int orientation = 0;
	Inventaire inventaire = new Inventaire();
	Boolean m_mort = false;
	Cellule m_cell;

    public Personnage(Model model, BufferedImage sprite, int x, int y, float scale) {
        m_model = model;
        img = sprite;
        this.x = x;
        this.y = y;
        m_scale = scale;
        splitSprite();
        projectile = new Item(13, -200,-200, m_model.m_spellSprite, m_model);
	    } 
	      m_cell.entité = this; 
	    if (m_cell.entité == null) { 
		m_cell = m_model.m_carte.cellules[y / 60][(x / 60)]; 

    }

    public void splitSprite() {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        m_sprites = new BufferedImage[4 * 4];
        m_w = width / 4;
        m_h = height / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = j * m_w;
                int y = i * m_h;

	public void droite() {
		if(m_mort == false) {
			if (x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
				Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
				Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
				if (cell.libre) {
					if (cell.entité instanceof Ennemi) {
						m_mort = true;
					}
					ramasser(cell);
					cell.entité = this;
					cellActuel.entité = null;

					x += Options.TAILLE_CELLULE;
					m_idx = 8 + (m_idx + 1) % 4;
					this.orientation = 1;
				}
			}
		}
	}

	public void haut() {
		if(m_mort == false) {
			if (y / Options.TAILLE_CELLULE != 0) {
				Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
				Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
				if (cell.libre) {
					if (cell.entité instanceof Ennemi) {
						m_mort = true;
					}
					ramasser(cell);
					cell.entité = this;
					cellActuel.entité = null;


					y -= Options.TAILLE_CELLULE;
					m_idx = 12 + (m_idx + 1) % 4;
					this.orientation = 3;
				}
			}
		}
	}

	public void bas() {
		if(m_mort == false) {
			if (y / Options.TAILLE_CELLULE != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE - 1)) {
				Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
				Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
				if (cell.libre) {
					if (cell.entité instanceof Ennemi) {
						m_mort = true;
					}
					ramasser(cell);
					cell.entité = this;
					cellActuel.entité = null;


					y += Options.TAILLE_CELLULE;
					m_idx = (m_idx + 1) % 4;
					this.orientation = 0;
				}
			}
		}
	}

	public void gauche() {
		if(m_mort == false) {
			if (x / Options.TAILLE_CELLULE != 0) {
				Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
				Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
				if (cell.libre) {
					if (cell.entité instanceof Ennemi) {
						m_mort = true;
					}
					ramasser(cell);
					cell.entité = this;
					cellActuel.entité = null;


					x -= Options.TAILLE_CELLULE;
					m_idx = 4 + (m_idx + 1) % 4;
					this.orientation = 2;
				}
			}
		}
	}
	
	
	public void paint(Graphics g) {
		Image img;
		if(m_mort == true) {
			img = m_model.m_mort;
		}
		else {
			img = m_sprites[m_idx];
		}
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x + 10, y, w, h, null);

    /*
     * void change_orientation() {
     *
     * if(this.orientation == 0) { m_idx=2; } if(this.orientation == 1) { m_idx=10;
     * } if(this.orientation == 2) { m_idx=6; } if(this.orientation == 3) {
     * m_idx=13; }
     *
     * }
     */
    public void paint(Graphics g) {

	public void ramasser(Cellule cell) {
		if(cell.entité!=null) {
			Item[] item = m_model.m_item;
			for (int i = 0; i < item.length; i++) {
				if (item[i] != null) {
					if (cell.x == item[i].x && cell.y == item[i].y) {
						item[i].possession = 1;
						inventaire.enfiler(item[i]);
						cell.entité = null;
					}
				}
			}
		}
	}

    public void ramasser(Cellule cell) {
        if (cell.entité != null) {
            Item[] item = m_model.m_item;
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null) {
                    if (cell.x == item[i].x && cell.y == item[i].y && cell.entité != null) {
                        item[i].possession = 1;
                        inventaire.enfiler(item[i]);
                        cell.entité = null;
                    }
                }
            }
        }

    }

}