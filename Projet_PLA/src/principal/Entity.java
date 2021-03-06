package principal;

import java.awt.image.BufferedImage;

import basic.Cellule;

public abstract class Entity {
	public int x, y;
	public BufferedImage img;
	public BufferedImage img2;
	public Model m_model;
	public Cellule m_cell;
	public int orientation = 0;

	public int[] PosToCell() {
		int i[] = new int[2];
		i[0] = y / Options.TAILLE_CELLULE;
		i[1] = x / Options.TAILLE_CELLULE;
		return i;
	}

	public abstract void move(String param);

	public abstract void threw();

	public abstract void hit();

	public abstract void pick(String param);

	public void turn(String param) {
		switch (param) {
		case "S":
			this.orientation = 0;
			break;
		case "N":
			this.orientation = 3;
			break;
		case "E":
			this.orientation = 1;
			break;
		case "O":
			this.orientation = 2;
			break;
		default:
			this.orientation = 0;
			break;
		}
	}

	public abstract void pop();

	public abstract void follow();

	protected int[] PosToCell(int x, int y) {
		int i[] = new int[2];
		i[0] = y / Options.TAILLE_CELLULE;
		i[1] = x / Options.TAILLE_CELLULE;
		return i;
	}

	public void wizz() {
		int x2 = x / Options.TAILLE_CELLULE;
		int y2 = y / Options.TAILLE_CELLULE;
		while (m_model.m_carte.cellules[y2][x2].libre == false || m_model.m_carte.cellules[y2][x2].entité != null) {
			x2 = (int) (Math.random() * (Options.nb_px_x_max - Options.nb_px_x_min)) / Options.TAILLE_CELLULE;
			y2 = (int) (Math.random() * (Options.nb_px_y_max - Options.nb_px_y_min)) / Options.TAILLE_CELLULE;
		}
		m_model.m_carte.cellules[y2][x2].entité = this;
		m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][x / Options.TAILLE_CELLULE].entité = null;
		x = x2 * Options.TAILLE_CELLULE;
		y = y2 * Options.TAILLE_CELLULE;
	}

}
