package automate;

import basic.Cellule;
import principal.Entity;
import principal.Options;

public class _Cell extends _Condition {

	public _Cell() {
	}

	@Override
	public boolean eval(Entity e) {
		String entity_cellule = tab_parametre[1]; // entité à la case testé
		switch (tab_parametre[1]) {
		/*
		 * case "OW": entity_cellule = "Obstacle"; break;
		 */
		case "T":
			entity_cellule = "principal.Personnage";
			break;
		case "A":
			entity_cellule = "principal.Ennemi";
			break;
		case "G":
			entity_cellule = "principal.Spawn";
			break;
		case "P":
			entity_cellule = "principal.Item";
			break;
		case "M":
			entity_cellule = "principal.Bonus";
			break;
		default:
			break;
		}
		switch (tab_parametre[0]) {
		case "E":
			if (e.x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
				Cellule cell = e.m_model.m_carte.cellules[e.y / Options.TAILLE_CELLULE][(e.x / Options.TAILLE_CELLULE)
						+ 1];
				if (!entity_cellule.equals("V") && !entity_cellule.equals("W")) {
					if (cell.entité == null) {
						return false;
					}
					return (cell.entité.getClass().getName() == entity_cellule);
				} else if (entity_cellule.equals("V")) {
					return (cell.libre && cell.entité == null);
				} else {
					return !(cell.libre);
				}
				/*
				 * if (entity_cellule == "W") { return (!cell.libre); }
				 */
			}
			return false;
		case "O":
			if (e.x / Options.TAILLE_CELLULE != 0) {
				Cellule cell = e.m_model.m_carte.cellules[e.y / Options.TAILLE_CELLULE][(e.x / Options.TAILLE_CELLULE)
						- 1];
				if (!entity_cellule.equals("V") && !entity_cellule.equals("W")) {
					if (cell.entité == null) {
						return false;
					}
					return (cell.entité.getClass().getName() == entity_cellule);
				} else if (entity_cellule.equals("V")) {
					return (cell.libre && cell.entité == null);
				} else {
					return !(cell.libre);
				}

			}
			return false;
		case "N":
			if (e.y / Options.TAILLE_CELLULE != 0) {
				Cellule cell = e.m_model.m_carte.cellules[(e.y / Options.TAILLE_CELLULE) - 1][e.x
						/ Options.TAILLE_CELLULE];
				if (!entity_cellule.equals("V") && !entity_cellule.equals("W")) {
					if (cell.entité == null) {
						return false;
					}
					return (cell.entité.getClass().getName() == entity_cellule);
				} else if (entity_cellule.equals("V")) {
					return (cell.libre && cell.entité == null);
				} else {
					return !(cell.libre);
				}

			}
			return false;
		case "S":
			if (e.y / Options.TAILLE_CELLULE != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE
					- 1)) {
				Cellule cell = e.m_model.m_carte.cellules[(e.y / Options.TAILLE_CELLULE) + 1][e.x
						/ Options.TAILLE_CELLULE];
				if (!entity_cellule.equals("V") && !entity_cellule.equals("W")) {
					if (cell.entité == null) {
						return false;
					}
					return (cell.entité.getClass().getName() == entity_cellule);
				} else if (entity_cellule.equals("V")) {
					return (cell.libre && cell.entité == null);
				} else {
					return !(cell.libre);
				}

			}
			return false;
		case "F":
			_Cell c = new _Cell();
			switch (e.orientation) {
			case 0:
				c.tab_parametre[0] = "S";
				break;
			case 1:
				c.tab_parametre[0] = "E";
				break;
			case 2:
				c.tab_parametre[0] = "O";
				break;
			case 3:
				c.tab_parametre[0] = "N";
				break;
			}
			c.tab_parametre[1] = this.tab_parametre[1];
			return c.eval(e);
		default:
			return false;
		}
	}
}
