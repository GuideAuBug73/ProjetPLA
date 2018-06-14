package principal;

import basic.Cellule;

public class Obstacle extends Entity {
	public Obstacle(Model model) {
		m_model = model;
		Cellule cell;
		int x = (int) (Math.random() * (Options.nb_cell_w - 3)) + 2;
		int y = (int) (Math.random() * (Options.nb_cell_h - 3)) + 2;
		cell = m_model.m_carte.cellules[y][x];
		while (!(cell.entité == null && cell.libre == false
				&& (m_model.m_carte.cellules[y][x + 1].libre || m_model.m_carte.cellules[y][x - 1].libre
						|| m_model.m_carte.cellules[y + 1][x].libre || m_model.m_carte.cellules[y - 1][x].libre))) {
			x = (int) (Math.random() * (Options.nb_cell_w - 3)) + 2;
			y = (int) (Math.random() * (Options.nb_cell_h - 3)) + 2;
			cell = m_model.m_carte.cellules[y][x];
		}
		m_model.m_carte.cellules[y][x].entité = this;
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
		Cellule cell;
		Cellule cellActuel;
		switch (param) {

		case "N":
			cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
			m_cell = cell;
			cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			cell.entité = this;
			cellActuel.entité = null;
			y -= Options.TAILLE_CELLULE;
			break;
		case "S":
			cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
			m_cell = cell;
			cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			cell.entité = this;
			cellActuel.entité = null;
			y += Options.TAILLE_CELLULE;
			break;
		case "O":
			cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
			m_cell = cell;
			cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			cell.entité = this;
			cellActuel.entité = null;
			x -= Options.TAILLE_CELLULE;
			break;
		case "E":
			cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
			m_cell = cell;
			cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			cell.entité = this;
			cellActuel.entité = null;
			x += Options.TAILLE_CELLULE;
			break;
		default:
			break;
		}

	}

	@Override
	public void follow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void threw() {
		// TODO Auto-generated method stub

	}

}
