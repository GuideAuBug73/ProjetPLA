package automate;

import principal.Entity;

public class Joueur_Proche extends _Condition {

	public Joueur_Proche() {
	}

	@Override
	public boolean eval(Entity e) {
		int distance = 5;
		int tab_a[] = e.PosToCell();
		int cell_a_x = tab_a[0];
		int cell_a_y = tab_a[1];		
		int tab_j[] = e.m_model.m_perso.PosToCell();
		int cell_j_x = tab_j[0];
		int cell_j_y = tab_j[1];
		System.out.println(((cell_j_x > cell_a_x - distance) && (cell_j_x < cell_a_x + distance) && (cell_j_y > cell_a_y - distance)
				&& (cell_j_y < cell_a_y + distance)));
		return ((cell_j_x > cell_a_x - distance) && (cell_j_x < cell_a_x + distance) && (cell_j_y > cell_a_y - distance)
				&& (cell_j_y < cell_a_y + distance));
	}
}