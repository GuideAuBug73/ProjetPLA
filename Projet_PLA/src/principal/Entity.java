package principal;

import java.awt.image.BufferedImage;

public abstract class Entity {
	public int x, y;
	public BufferedImage img;
	public Model m_model;

	public int[] PosToCell(int x, int y) {
		int i[] = new int[2];
		i[0] = y / Options.TAILLE_CELLULE;
		i[1] = x / Options.TAILLE_CELLULE;
		return i;
	}

	public void move(String param) {
		switch (param) {

		case "N":
			y -= Options.TAILLE_CELLULE;
			break;
		case "S":
			y += Options.TAILLE_CELLULE;
			break;
		case "O":
			x -= Options.TAILLE_CELLULE;
			break;
		case "E":
			x += Options.TAILLE_CELLULE;
			break;
		default:
			break;
		}
	}

	public abstract void hit();

	public abstract void pick();

	public abstract void turn(String param);

	public abstract void wizz();

	public abstract void pop();

}
