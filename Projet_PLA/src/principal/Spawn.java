package principal;

import java.awt.image.BufferedImage;

import basic.Cellule;

public class Spawn extends Entity {
	boolean presence = false;

	public Spawn(Cellule c, BufferedImage image, Model model) {
		img = image;
		m_model = model;
		x = c.x;
		y = c.y;
	}

	public Spawn(int x, int y, BufferedImage image, Model model) {
		m_model = model;
		img = image;
		this.x = x;
		this.y = y;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
