package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Spawn extends Entity {
	public boolean presence = false;
	BufferedImage m_sprite;
	Model m_model;
	int x,y;

	public Spawn(Cellule c, BufferedImage image, Model model) {
		m_sprite = image;
		m_model = model;
		x = c.x;
		y = c.y;
	}

	public Spawn(int x, int y, BufferedImage image, Model model) {
		m_model = model;
		m_sprite = image;
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		g.drawImage(m_sprite, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick(String param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(String param) {
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

	@Override
	public void move(String param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void follow() {
		// TODO Auto-generated method stub
		
	}


}
