package principal;

import java.awt.Graphics;
import java.awt.Image;

import basic.Position;
import java.awt.image.BufferedImage;

public class Ennemi extends Personnage {
	boolean boss;
	// nombre de cellules
	int m_w; // ncols
	int m_h; // nrows
	// taille de la fenetre
	int m_sizew;
	int m_sizeh;
	float m_scale;
	Model model;
	BufferedImage[] m_sprites = new BufferedImage[100];
	int m_step = 8;
	int m_nsteps;
	int m_idx;
	BufferedImage m_sprite;
	long m_lastMove, m_lastReverse;
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

		for (int i = 0; i < m_w - 1; i++) {
			for (int j = 0; j < m_h; j++) {
				int x = j * m_sizew;
				int y = i * m_sizeh;
				m_sprites[(i * m_h) + j] = Img.getSubimage(0, 0, m_sizew, m_sizeh+13);
			}
		}
	}
	void step(long now) {
	    
	    long elapsed = now - m_lastMove;
	    if (elapsed > 60L) {
	      m_lastMove = now;
	      int nsteps = m_w * m_h;

	      if (m_nsteps < nsteps) {
	        p.x += m_step;
	        m_nsteps++;
	      } else if (m_nsteps < 2 * nsteps) {
	        p.x -= m_step;
	        m_nsteps++;
	      } else {
	        m_nsteps = 0;
	      }
	      m_idx = (m_idx + 1) % m_sprites.length;
	    }
	  }
	void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		   int w = (int)(m_scale * m_sizew);
		   int h = (int)(m_scale * m_sizeh);
		g.drawImage(img, p.x, p.y, w, h, null);
	}
}
