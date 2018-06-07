
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
		m_cell = m_model.m_carte.cellules[y / 60][(x / 60)]; 
	    if (m_cell.entité == null) { 
	      m_cell.entité = this; 
	    } 
		splitSprite();
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

				m_sprites[(i * 4) + j] = img.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void droite() {
		if (x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
			Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
			if (cell.libre) {
				if (cell.entité instanceof Ennemi) {
					m_mort = true;
				}
				cell.entité = this;
				cellActuel.entité = null;
				ramasser(cell);
				x += Options.TAILLE_CELLULE;
				if (m_idx == 10)
					m_idx = 11;
				else
					m_idx = 10;
				this.orientation = 1;
			}
		}
	}

	public void haut() {
		if (y / Options.TAILLE_CELLULE != 0) {
			Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
			if (cell.libre) {
				if (cell.entité instanceof Ennemi) {
					m_mort = true;
				}
				cell.entité = this;
				cellActuel.entité = null;
				ramasser(cell);

				y -= Options.TAILLE_CELLULE;
				if (m_idx == 13)
					m_idx = 14;
				else
					m_idx = 13;
				this.orientation = 3;
			}
		}
	}

	public void bas() {
		if (y / Options.TAILLE_CELLULE != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE - 1)) {
			Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
			if (cell.libre) {
				if (cell.entité instanceof Ennemi) {
					m_mort = true;
				}
				cell.entité = this;
				cellActuel.entité = null;
				ramasser(cell);

				y += Options.TAILLE_CELLULE;
				if (m_idx == 2)
					m_idx = 1;
				else
					m_idx = 2;
				this.orientation = 0;
			}
		}
	}

	public void gauche() {
		if (x / Options.TAILLE_CELLULE != 0) {
			Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)]; 
			if (cell.libre) {
				if (cell.entité instanceof Ennemi) {
					m_mort = true;
				}
				cell.entité = this;
				cellActuel.entité = null;
				ramasser(cell);

				x -= Options.TAILLE_CELLULE;
				if (m_idx == 6)
					m_idx = 5;
				else
					m_idx = 6;
				this.orientation = 2;
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

	}

	public void ramasser(Cellule cell) {
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