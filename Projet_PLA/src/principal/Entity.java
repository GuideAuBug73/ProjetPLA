package principal;

import java.awt.image.BufferedImage;

import basic.Cellule;

public abstract class Entity {
	public int x, y;
	public BufferedImage img;
	public BufferedImage img2;
	public Model m_model;
	public Cellule m_cell;

	public int[] PosToCell(int x, int y) {
		int i[] = new int[2];
		i[0] = y / Options.TAILLE_CELLULE;
		i[1] = x / Options.TAILLE_CELLULE;
		return i;
	}

	public abstract void move(String param);
		

	public abstract void hit();

	public abstract void pick(String param);

	public abstract void turn(String param);

	public abstract void wizz();

	public abstract void pop();

}
