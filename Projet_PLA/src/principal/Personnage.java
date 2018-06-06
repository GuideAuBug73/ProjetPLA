
package principal;

import basic.Cellule;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {
	int m_w, m_h;
	int m_idx;
	float m_scale;
	BufferedImage[] m_sprites;
	Model m_model;
	int orientation = 0;

	public Personnage(Model model, BufferedImage sprite, int x, int y, float scale) {
		m_model = model;
		img = sprite;
		this.x = x;
		this.y = y;
		m_scale = scale;
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

		Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];

		if (cell.libre) {

			x += Options.TAILLE_CELLULE;

			if (m_idx == 10)
				m_idx = 11;
			else
				m_idx = 10;
			this.orientation = 1;
		}
	}

	public void haut() {
		Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];

		if (cell.libre) {

			y -= Options.TAILLE_CELLULE;

			if (m_idx == 13)
				m_idx = 14;
			else
				m_idx = 13;
			this.orientation = 3;
		}
	}

	public void bas() {

		Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
		// int x = cell.x ;
		// int y = cell.y ;
		if (cell.libre) {
			y += Options.TAILLE_CELLULE;
			if (m_idx == 2)
				m_idx = 1;
			else
				m_idx = 2;
			this.orientation = 0;
		}
	}

	public void gauche() {
		Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];

		if (cell.libre) {
			x -= Options.TAILLE_CELLULE;
			if (m_idx == 6)
				m_idx = 5;
			else
				m_idx = 6;
			this.orientation = 2;

		}
	}

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
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x, y, w, h, null);

	}

}