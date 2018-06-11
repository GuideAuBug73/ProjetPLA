package principal;

import java.awt.image.BufferedImage;

import basic.Cellule;

public class Spawn extends Entity {
	public Entity entit;

	public Spawn(Cellule c, BufferedImage image, Model model,Entity m_entit) {
		img = image;
		m_model = model;
		x = c.x;
		y = c.y;
		entit = m_entit;
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

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
	}


}
