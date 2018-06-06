
package principal;

import basic.Cellule;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {
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

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}