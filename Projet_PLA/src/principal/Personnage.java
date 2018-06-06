
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
	int m_orientation = 0;
	Cellule m_cell;
	Boolean m_mort = false;

	public Personnage(Model model, BufferedImage sprite, int x, int y, float scale) {
		m_model = model;
		m_sprite = sprite;
		m_x = x;
		m_y = y;
		m_scale = scale;
		m_idx = 0;
		m_cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		if (m_cell.entité == null) {
			m_cell.entité = this;
		}
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
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) + 1];
		if (cell.libre) {
			if(cell.entité instanceof Ennemi) {
				m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_x += 60;

			m_idx = 8 + (m_idx + 1) % 4;
			this.m_orientation = 1;
			System.out.println("mort :"+m_mort);
		}
	}

	public void haut() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[(m_y / 60) - 1][m_x / 60];
		if (cell.libre) {
			if(cell.entité instanceof Ennemi) {
				m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;

			m_y -= 60;

			m_idx = 12 + (m_idx + 1) % 4;

			this.m_orientation = 3;
			System.out.println("mort :"+m_mort);
		}
	}

	public void bas() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[(m_y / 60) + 1][m_x / 60];
		if (cell.libre) {
			if(cell.entité instanceof Ennemi) {
				m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_y += 60;

			m_idx = (m_idx + 1) % 4;

			this.m_orientation = 0;
			System.out.println("mort :"+m_mort);
		}
	}

	public void gauche() {
		Cellule cellActuel = m_model.m_carte.cellules[m_y / 60][(m_x / 60)];
		Cellule cell = m_model.m_carte.cellules[m_y / 60][(m_x / 60) - 1];

		if (cell.libre) {
			if(cell.entité instanceof Ennemi) {
				m_mort = true;
			}
			cell.entité = this;
			cellActuel.entité = null;
			m_x -= 60;

			m_idx = 4 + (m_idx + 1) % 4;

			this.m_orientation = 2;
			System.out.println("mort :"+m_mort);
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
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}