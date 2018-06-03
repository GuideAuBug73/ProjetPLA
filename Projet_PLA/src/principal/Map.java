package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Map {
	// tableau de cellules
	Cellule cellules[][] = new Cellule[30][50];
	// nombre de cellules
	int m_w;
	int m_h;
	// taille de la fenetre
	int m_sizew;
	int m_sizeh;
	Entity a = null;
	// sprites
	BufferedImage m_spritefield;
	BufferedImage m_spritewall;

	Map(int h, int w, int sizeh, int sizew, BufferedImage spritewall, BufferedImage spritefield) {
		int nombreAleatoire = 0;
		m_w = w;
		m_h = h;
		m_sizew = sizew;
		m_sizeh = sizeh;
		m_spritefield = spritefield;
		m_spritewall = spritewall;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				nombreAleatoire = (int) (Math.random() * (10 - 0));
				// mur
				if (nombreAleatoire == 0)
					cellules[i][j] = new Cellule(null, false, j * m_sizew / m_w, i * m_sizeh / m_h);
				// pas de mur
				else
					cellules[i][j] = cellules[i][j] = new Cellule(null, true, j * m_sizew / m_w, i * m_sizeh / m_h);
			}
		}
	}

	void paint(Graphics g) {
		Image img;
		for (int i = 0; i < m_w; i++) {
			for (int j = 0; j < m_h; j++) {
				if (cellules[i][j].libre == true) {
					img = m_spritefield;
				} else
					img = m_spritewall;
				g.drawImage(img, m_w, m_h, cellules[i][j].x, cellules[i][j].y, null);
			}
		}
	}
}