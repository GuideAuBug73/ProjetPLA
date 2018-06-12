package automate;

import basic.Cellule;
import basic.Orientation;
import principal.Entity;
import principal.Options;

public class _Cell extends _Condition {
	

	public _Cell() {
	}

	@Override
	public boolean eval(Entity e) {
		String entity_cellule = tab_parametre[1] ; // entité à la case testé
		switch (tab_parametre[1]) {
		/*
		 * case "OW": entity_cellule = "Obstacle"; break;
		 */
		case "T":
			entity_cellule = "Personnage";
			break;
		case "A":
			entity_cellule = "Ennemi";
			break;
		case "G":
			entity_cellule = "Spawn";
			break;
		case "P":
			entity_cellule = "Item";
			break;
		default:
			break;
		}
		switch (tab_parametre[0]) {
		case "E":
			if (e.x + 1 / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
				Cellule cell = e.m_model.m_carte.cellules[e.y / Options.TAILLE_CELLULE][(e.x / Options.TAILLE_CELLULE)
						+ 1];
				if ((entity_cellule != "V") || (entity_cellule != "W")) {
					return (cell.entité.getClass().getName() == entity_cellule);
				}
				if (entity_cellule == "V") {
					return (cell.libre);
				}
				/*
				 * if (entity_cellule == "W") { return (!cell.libre); }
				 */
			}
			return false;
		case "O":
			if (e.x / Options.TAILLE_CELLULE -1 != 0) {
				Cellule cell = e.m_model.m_carte.cellules[e.y / Options.TAILLE_CELLULE][(e.x / Options.TAILLE_CELLULE)
						- 1];
				if ((entity_cellule != "V") || (entity_cellule != "W")) {
					return (cell.entité.getClass().getName() == entity_cellule);
				}
				if (entity_cellule != "V") {
					return (cell.libre);
				}

			}
			return false;
		case "N":
			if (e.y / Options.TAILLE_CELLULE -1 != 0) {
				Cellule cell = e.m_model.m_carte.cellules[(e.y / Options.TAILLE_CELLULE) - 1][e.x
						/ Options.TAILLE_CELLULE];
				if ((entity_cellule != "V") || (entity_cellule != "W")) {
					return (cell.entité.getClass().getName() == entity_cellule);
				}
				if (entity_cellule != "V") {
					return (cell.libre);
				}

			}
			return false;
		case "S":
			if (e.y / Options.TAILLE_CELLULE +1 != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE
					- 1)) {
				Cellule cell = e.m_model.m_carte.cellules[(e.y / Options.TAILLE_CELLULE) + 1][e.x
						/ Options.TAILLE_CELLULE];
				if ((entity_cellule != "V") || (entity_cellule != "W")) {
					return (cell.entité.getClass().getName() == entity_cellule);
				}
				if (entity_cellule != "V") {
					return (cell.libre);
				}

			}
			return false;
		default:
			return false;
		}
	}
}
