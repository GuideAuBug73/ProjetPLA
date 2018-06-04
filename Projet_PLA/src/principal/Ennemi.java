package principal;

import java.awt.Graphics;
import java.awt.Image;

import basic.Position;
import java.awt.image.BufferedImage;

public class Ennemi extends Personnage {
	boolean boss;
	// nombre de cellules
	int m_w; //ncols 
	int m_h; //nrows
	// taille de la fenetre
	int m_sizew;
	int m_sizeh;
	float m_scale;
	Model model;
	BufferedImage[] m_sprites = new BufferedImage[100];
	int m_step = 8;
	int m_idx;
	BufferedImage m_sprite;
	/* Automate IA; */

	Ennemi(Model model, int h, int w, int sizeh, int sizew, boolean boss, Position p, BufferedImage img, float scale) {
		if (boss) {
			this.boss = true;
		} else {

			this.boss = false;
		}
		m_w = w;
		m_h = h;
		m_sizew = sizew;
		m_sizeh = sizeh;
		m_scale = scale;
		this.p = p;
		m_sprite = img;
		Img = img;
		splitSprite();
	}

	void splitSprite() {
		int width = Img.getWidth(null);
		int height = Img.getHeight(null);
		m_sprites = new BufferedImage[m_sizew * m_sizeh];
		m_sizew = width / m_w;
		m_sizeh = height / m_h;

		for (int i = 0; i < m_w; i++) {
			for (int j = 0; j < m_h; j++) {
				int x = j * m_sizew;
				int y = i * m_sizeh;
				m_sprites[(i * m_h) + j] = Img.getSubimage(x, y, m_sizew, m_sizeh);
			}
		}
	}

	void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		g.drawImage(img, p.x, p.y, m_sizew , m_sizeh , null);
	}
}
