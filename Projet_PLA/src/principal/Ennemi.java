package principal;

import basic.Cellule;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Ennemi extends Entity {

	BufferedImage m_sprite;
	int m_w, m_h;
	int m_x, m_y;
	int m_idx;
	float m_scale;
	BufferedImage[] m_sprites;
	Model m_model;
	int orientation = 0;
	Cellule m_cell;
	Boolean m_mort = false;

	public Ennemi(Model model, BufferedImage sprite, int x, int y, float scale) {
		m_model = model;
		m_sprite = sprite;
		m_x = x;
		m_y = y;
		m_scale = scale;
		m_cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		if (m_cell.entité == null) {
			m_cell.entité = this;
		}
		splitSprite();
	}

	public void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[4 * 3];
		m_w = width / 3;
		m_h = height / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				int x = j * m_w;
				int y = i * m_h;

				m_sprites[(i * 3) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void droite() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) + 1];

		if (cell.libre) {
			if (cell.entité instanceof Personnage) {
				m_model.m_perso.m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_x += 60;

			m_idx = 6 + (m_idx + 1) % 3;
			this.orientation = 1;
		}
	}

	public void haut() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[(m_y / 60) - 1][m_x / 60];

		if (cell.libre) {
			if (cell.entité instanceof Personnage) {
				m_model.m_perso.m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_y -= 60;

			m_idx = 9 + (m_idx + 1) % 3;

			this.orientation = 3;
		}
	}

	public void bas() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[(m_y / 60) + 1][m_x / 60];
		if (cell.libre) {
			if (cell.entité instanceof Personnage) {
				m_model.m_perso.m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_y += 60;

			m_idx = (m_idx + 1) % 3;

			this.orientation = 0;
		}
	}

	public void gauche() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) - 1];

		if (cell.libre) {
			if (cell.entité instanceof Personnage) {
				m_model.m_perso.m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_x -= 60;

			m_idx = 3 + (m_idx + 1) % 3;

			this.orientation = 2;

		}
	}

	public void paint(Graphics g) {
		if(m_model.m_perso.m_mort != true) {
			Image img = m_sprites[m_idx];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, m_x, m_y, w, h, null);
		}
	}

}
