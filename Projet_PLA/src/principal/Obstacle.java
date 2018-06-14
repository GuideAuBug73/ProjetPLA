package principal;

import basic.Cellule;

public class Obstacle extends Entity {
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
