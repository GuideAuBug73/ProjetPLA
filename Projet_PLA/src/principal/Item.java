package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Item extends Entity {
	public int type;
	public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi

	public Item(int type, int w, int h, BufferedImage m_item, Model model) {
		this.type = type;
		m_model = model;
		x = w;
		y = h;
		img = m_item;
	}

	public Item(int type, Cellule cell, BufferedImage m_item, Model model) {
		this.type = type;
		m_model = model;
		x = cell.x;
		y = cell.y;
		img = m_item;
	}

	public void paint(Graphics g) {
		if (possession == 0)
			g.drawImage(img, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
	}

	public void paint(Graphics g, int w, int h) {
		Image img = this.img;
		if (possession == 1)
			g.drawImage(img, w, h, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
	}
	
	public void move() {
	}
}