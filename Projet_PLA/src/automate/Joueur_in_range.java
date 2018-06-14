package automate;

import principal.Ennemi;
import principal.Entity;
import principal.Item;
import principal.Obstacle;
import principal.Options;
import principal.Personnage;

public class Joueur_in_range extends _Condition {
	public Joueur_in_range() {
	}

	@Override
	public boolean eval(Entity e) {
		assert (e instanceof Ennemi);
		int i;
		int tab[] = e.PosToCell();
		int cell_x = tab[0];
		int cell_y = tab[1];
		Ennemi mechant = (Ennemi) e;
		Item objet = mechant.m_item;
		i = 0;
		switch (mechant.orientation) {
		case 0:// nord

			while (!(e.x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)
					|| (e.y / Options.TAILLE_CELLULE - i != 0))) {

				if (e.m_model.m_carte.cellules[cell_y - i][cell_x].entité instanceof Personnage) {
					return true;
				}
				if (e.m_model.m_carte.cellules[cell_y - i][cell_x].entité instanceof Obstacle) {
					return false;
				}
				if (!e.m_model.m_carte.cellules[cell_y - i][cell_x].libre) {
					return false;
				}
				if ((objet.type > 1) && i > 6) {
					return false;
				}
			}

			return false;

		case 1:// est
			while (!(e.x / Options.TAILLE_CELLULE + i != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)
					|| (e.y / Options.TAILLE_CELLULE != 0))) {

				if (e.m_model.m_carte.cellules[cell_y][cell_x + i].entité instanceof Personnage) {
					return true;
				}
				if (e.m_model.m_carte.cellules[cell_y][cell_x + i].entité instanceof Obstacle) {
					return false;
				}
				if (!e.m_model.m_carte.cellules[cell_y][cell_x + i].libre) {
					return false;
				}
				if ((objet.type > 1) && i > 6) {
					return false;
				}
			}

			return false;

		case 2:// west
			while (!(e.x / Options.TAILLE_CELLULE - i != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)
					|| (e.y / Options.TAILLE_CELLULE != 0))) {

				if (e.m_model.m_carte.cellules[cell_y][cell_x - i].entité instanceof Personnage) {
					return true;
				}
				if (e.m_model.m_carte.cellules[cell_y][cell_x - i].entité instanceof Obstacle) {
					return false;
				}
				if (!e.m_model.m_carte.cellules[cell_y][cell_x - i].libre) {
					return false;
				}
				if ((objet.type > 1) && i > 6) {
					return false;
				}
			}

			return false;
		case 3:// sud
			while (!(e.x / Options.TAILLE_CELLULE  != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)
					|| (e.y / Options.TAILLE_CELLULE +i!= 0))) {

				if (e.m_model.m_carte.cellules[cell_y +i][cell_x].entité instanceof Personnage) {
					return true;
				}
				if (e.m_model.m_carte.cellules[cell_y +i][cell_x].entité instanceof Obstacle) {
					return false;
				}
				if (!e.m_model.m_carte.cellules[cell_y +i][cell_x].libre) {
					return false;
				}
				if ((objet.type > 1) && i > 6) {
					return false;
				}
			}

			return false;
			default : return false;
		}
	}
}
