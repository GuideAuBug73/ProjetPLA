package principal;

import basic.Cellule;

public class Obstacle extends IA {

	public Obstacle(Model model) {
		m_model = model;
		Cellule cell;
		int x = (int) (Math.random() * (Options.nb_cell_w-3))+2;
		int y = (int) (Math.random() * (Options.nb_cell_h-3))+2;
		cell = m_model.m_carte.cellules[y][x];
		while(!(cell.entité == null && cell.libre == false && (m_model.m_carte.cellules[y][x + 1].libre
				|| m_model.m_carte.cellules[y][x - 1].libre || m_model.m_carte.cellules[y + 1][x].libre
				|| m_model.m_carte.cellules[y - 1][x].libre))) {
			x = (int) (Math.random() * (Options.nb_cell_w-3))+2;
			y = (int) (Math.random() * (Options.nb_cell_h-3))+2;
			cell = m_model.m_carte.cellules[y][x];
		}
		m_model.m_carte.cellules[y][x].entité = this;
	}

	/*public void move() {
		for(int i=0 ; i<Options.nb_cell_h ; i++) {
			for(int j=0 ; j<Options.nb_cell_w ; j++) {
				if(m_model.m_carte.cellules[i][j].entité instanceof Obstacle) {
					int orientation = (int) (Math.random() * 4);
					switch(orientation) {
					case 0: 
						if(m_model.m_carte.cellules[i+1][j].libre == true) {
							m_model.m_carte.cellules[i+1][j].entité = this;
							m_model.m_carte.cellules[i+1][j].libre = false;
							m_model.m_carte.cellules[i][j].entité = null;
							m_model.m_carte.cellules[i][j].libre = true;
						}
					break;
					case 1: 
						if(m_model.m_carte.cellules[i][j+1].libre == true) {
							m_model.m_carte.cellules[i][j+1].entité = this;
							m_model.m_carte.cellules[i][j+1].libre = false;
							m_model.m_carte.cellules[i][j].entité = null;
							m_model.m_carte.cellules[i][j].libre = true;
						}
					break;
					case 2: 
						if(m_model.m_carte.cellules[i][j-1].libre == true) {
							m_model.m_carte.cellules[i][j-1].entité = this;
							m_model.m_carte.cellules[i][j-1].libre = false;
							m_model.m_carte.cellules[i][j].entité = null;
							m_model.m_carte.cellules[i][j].libre = true;
						}
					break;
					case 3: 
						if(m_model.m_carte.cellules[i-1][j].libre == true) {
							m_model.m_carte.cellules[i-1][j].entité = this;
							m_model.m_carte.cellules[i-1][j].libre = false;
							m_model.m_carte.cellules[i][j].entité = null;
							m_model.m_carte.cellules[i][j].libre = true;
						}
					break;
					}
				}
			}
		}
	}*/
}
