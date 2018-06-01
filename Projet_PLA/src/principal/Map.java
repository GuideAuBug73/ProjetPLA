package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Map {
	//tableau de cellules
	Cellule cellules[][];
	//nombre de cellules
	int m_w;
	int m_h;
	//taille de la fenetre
	int m_sizew;
	int m_sizeh;
	//sprites
	BufferedImage m_spritefield;
	BufferedImage m_spritewall;
	
	Map(int h, int w, int sizew, int sizeh, BufferedImage spritewall, BufferedImage spritefield) {
		int nombreAleatoire = 0;
		m_w = w;
		m_h = h;
		m_sizew = sizew;
		m_sizeh = sizeh;
		m_spritefield = spritefield;
		m_spritewall = spritewall;
		for (int i = 1; i <= w; i++)
		{
			for(int j = 1; j <= h; j++) {
				nombreAleatoire = (int)(Math.random() * (10 - 0));
				//mur
				if(nombreAleatoire==0)
					cellules[i][j].libre = true;
				//pas de mur
				cellules[i][j].libre = false;
				cellules[i][j].x = (j-1)*m_sizew/m_w;
				cellules[i][j].y = (i-1)*m_sizeh/m_h;
			}
		}
	}
	
	void paint(Graphics g) {
		Image img;
		for (int i = 1; i <= m_w; i++)
		{
			for(int j = 1; j <= m_h; j++) {
				if(cellules[i][j].libre == true) {
					img = m_spritefield;
				}
				else img = m_spritewall;
				g.drawImage(img, m_w, m_h, cellules[i][j].x, cellules[i][j].y, null);
			}
		}
	}
}