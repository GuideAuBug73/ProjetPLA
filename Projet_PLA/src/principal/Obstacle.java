package principal;

import basic.Cellule;

public class Obstacle extends IA {

	public Obstacle() {
		Cellule cell;
		int x = (int) (Math.random() * Options.nb_cell_w);
		int y = (int) (Math.random() * Options.nb_cell_h);
		cell = m_model.m_carte.cellules[x][y];
		if(cell.entité == null && cell.libre == false && m_model.m_carte.cellules[x+1][y].libre && m_model.m_carte.cellules[x-1][y].libre && m_model.m_carte.cellules[x][y+1].libre && m_model.m_carte.cellules[x][y-1].libre) {
			cell.entité = this;
		}
	}
}
