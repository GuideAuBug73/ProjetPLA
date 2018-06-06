
package principal;

import basic.Cellule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {
	int m_w, m_h;
	int m_idx;
	float m_scale;
	BufferedImage[] m_sprites;
	Model m_model;
	Inventaire inventaire = new Inventaire();
	int m_orientation = 0;
	Cellule m_cell;
	Boolean m_mort = false;

	public Personnage(Model model, BufferedImage sprite, int x, int y, float scale) {
		m_model = model;
		img = sprite;
		this.x = x;
		this.y = y;
		m_scale = scale;
		m_idx = 0;
		m_cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
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

	/*
	 * public int x_cell_pixel(Cellule c ) {
	 * 
	 * return c.x *60 ;
	 * 
	 * } public int y_cell_pixel(Cellule c ) {
	 * 
	 * return c.y *60 ; }
	 */

	public void droite() {

		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) + 1];

		if (cell.libre) {

			m_x += 60;

			m_idx = 8 + (m_idx + 1) % 4;
			this.orientation = 1;
		}
	}

	public void haut() {
		Cellule cell = m_model.m_carte.cellules[(m_y / 60) - 1][m_x / 60];

		if (cell.libre) {

			m_y -= 60;

			m_idx = 12 + (m_idx + 1) % 4;

			this.orientation = 3;
		}
	}

	public void bas() {

		Cellule cell = m_model.m_carte.cellules[(m_y / 60) + 1][m_x / 60];
		if (cell.libre) {

			m_y += 60;

			m_idx = (m_idx + 1) % 4;

			this.orientation = 0;
		}
	}

	public void gauche() {
		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) - 1];

		if (cell.libre) {

			m_x -= 60;

			m_idx = 4 + (m_idx + 1) % 4;

			this.orientation = 2;

		}
	}
	
	public void paint(Graphics g) {
		Image img;
		if(m_mort == true) {
			img = m_model.m_itemMort;
		}
		else {
			img = m_sprites[m_idx];			
		}
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x, y, w, h, null);

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