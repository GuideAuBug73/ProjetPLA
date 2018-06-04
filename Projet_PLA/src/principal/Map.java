package principal;
import basic.Arbre;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Map {
	// tableau de cellules
	public Cellule cellules[][] = new Cellule[30][50];
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
	Arbre chemin;
	int compteur = 0;

	public Map(int h, int w, int sizeh, int sizew, BufferedImage spritewall, BufferedImage spritefield,Cellule start) {
		int nombreAleatoire = 0;
		m_w = w;
		m_h = h;
		m_sizew = sizew;
		m_sizeh = sizeh;
		m_spritefield = spritefield;
		m_spritewall = spritewall;
		chemin = new Arbre(start);
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
	
	public boolean testMap(Spawn mob[],Arbre a) {
		cellules[a.c.x][a.c.y].visite = true;
		for (int i = 0; i < mob.length;i++) {
			if(!testSpawn(mob[i],a))
					return false;
		}
		return true;
			
	}
	
	
	
	private boolean testSpawn(Spawn s, Arbre a) {
		if(a.c.x == s.x && a.c.y == s.y) {
			return true;
		}
		if(a.c.x >= 0 && a.c.y+1 >=0 && !(cellules[a.c.x][a.c.y-1].visite))
		{
			cellules[a.c.x][a.c.y-1].visite = true;
			a.filsN.c = cellules[a.c.x][a.c.y-1];
			if(a.filsN.c.libre && testSpawn(s,a.filsN)) {
				return true;
			}
			
		}
		if(a.c.x+1 >= 0 && a.c.y >=0 && !(cellules[a.c.x+1][a.c.y].visite)) {
			cellules[a.c.x+1][a.c.y].visite = true;
			a.filsE.c = cellules[a.c.x+1][a.c.y];
			if(a.filsE.c.libre && testSpawn(s,a.filsE)) {
				return true;
			}
		}
		if(a.c.x >= 0 && a.c.y+1 >=0 && !(cellules[a.c.x][a.c.y+1].visite)) {
			cellules[a.c.x][a.c.y+1].visite = true;
			a.filsS.c = cellules[a.c.x][a.c.y+1];
			if(a.filsS.c.libre && testSpawn(s,a.filsS)) {
				return true;
			}
		
		}
		if(a.c.x-1 >= 0 && a.c.y >=0 && !(cellules[a.c.x-1][a.c.y].visite)) {
			cellules[a.c.x-1][a.c.y].visite = true;
			a.filsO.c = cellules[a.c.x-1][a.c.y];
			if(a.filsO.c.libre && testSpawn(s,a.filsO)) {
				return true;
			}
		}
		return false;
		
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