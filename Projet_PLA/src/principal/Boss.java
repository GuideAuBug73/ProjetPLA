package principal;

import basic.Cellule;
import pathfinding.Grid2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

public class Boss extends Entity {
	int m_w, m_h;
	int m_idx = 0, m_idxMort = 0;
	float m_scale;
	BufferedImage[] m_sprites, m_Expsprites;
	Cellule m_cell;
	Item m_item;
	int m_cpt;
	int vie = 10;
	public Item projectile;
	boolean mort;
	boolean Ennemi_presence = false;
	public BufferedImage[] m_spritesMort;
	public int m_w2;
	public int m_h2;

	public Boss(Model model, BufferedImage sprite, BufferedImage sprite2, int x, int y, float scale) {
		m_model = model;
		mort = false;
		img = sprite;
		img2 = sprite2;
		m_item = null;
		this.x = x;
		this.y = y;
		m_scale = scale;
		m_cell = m_model.m_carte.cellules[y / 60][(x / 60)];
		projectile = new Item(14, -200, -200, m_model.m_fireSprite, m_model.m_exploSprite, m_model);
		if (m_cell.entité == null) {
			m_cell.entité = this;
		}
		m_cpt = 0;
		splitSprite();
	}

	public void splitSprite() {
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		m_sprites = new BufferedImage[4 * 4];
		m_w = width / 4;
		m_h = height / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * 4) + j] = img.getSubimage(x, y, m_w, m_h);
			}
		}
		width = img2.getWidth(null);
		height = img2.getHeight(null);
		m_spritesMort = new BufferedImage[8 * 4];
		m_w2 = width / 4;
		m_h2 = height / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = j * m_w2;
				int y = i * m_h2;
				m_spritesMort[(i * 4) + j] = img2.getSubimage(x, y, m_w2, m_h2);
			}
		}
	}

	void createmy() {

		if (orientation == 0 || orientation == 3) {
			Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
			if (cell.libre)
				m_model.bossc((x / 60) + 1, y / 60);
			cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
			if (cell.libre)
				m_model.bossc((x / 60) - 1, y / 60);
		}
		if (orientation == 1 || orientation == 2) {
			Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][(x / Options.TAILLE_CELLULE)];
			if (cell.libre)
				m_model.bossc((x / 60), (y / 60) + 1);
			cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][(x / Options.TAILLE_CELLULE)];
			if (cell.libre)
				m_model.bossc((x / 60), (y / 60) - 1);
		}
	}

	public void droite() {
		if (x / Options.TAILLE_CELLULE != (Options.nb_px_x_max / Options.TAILLE_CELLULE - 1)) {
			Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			if (cell.libre && !(cell.entité instanceof Ennemi)) {
				if (cell.entité instanceof Personnage) {
					m_model.m_perso.m_mort = true;
				} else if (cell.entité instanceof Item) {
					m_item = (Item) cell.entité;
					m_item.possession = 2;
					m_item = null;
					cell.entité = null;
				} else if (cell.entité instanceof Bonus) {
					cell.entité.x = -100;
					cell.entité.y = -100;
					cell.entité = null;
				}
				cell.entité = this;
				cellActuel.entité = null;
				x += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 8 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 24 + (m_idx + 1) % 4;
				}
				this.orientation = 1;
			}
		}
	}

	public void haut() {
		if (y / Options.TAILLE_CELLULE != 0) {
			Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			if (cell.libre && !(cell.entité instanceof Ennemi)) {
				if (cell.entité instanceof Personnage) {
					m_model.m_perso.m_mort = true;
				} else if (cell.entité instanceof Item) {
					m_item = (Item) cell.entité;
					m_item.possession = 2;
					m_item = null;
					cell.entité = null;
				} else if (cell.entité instanceof Bonus) {
					cell.entité.x = -100;
					cell.entité.y = -100;
					cell.entité = null;
				}
				cell.entité = this;
				cellActuel.entité = null;
				y -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 12 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 28 + (m_idx + 1) % 4;
				}

				this.orientation = 3;
			}
		}
	}

	public void bas() {
		if (y / Options.TAILLE_CELLULE != ((Options.nb_px_y_max - Options.nb_px_y_min) / Options.TAILLE_CELLULE - 1)) {
			Cellule cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			if (cell.libre && !(cell.entité instanceof Ennemi)) {
				if (cell.entité instanceof Personnage) {
					m_model.m_perso.m_mort = true;
				} else if (cell.entité instanceof Item) {
					m_item = (Item) cell.entité;
					m_item.possession = 2;
					m_item = null;
					cell.entité = null;
				} else if (cell.entité instanceof Bonus) {
					cell.entité.x = -100;
					cell.entité.y = -100;
					cell.entité = null;
				}
				cell.entité = this;
				cellActuel.entité = null;
				y += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 16 + (m_idx + 1) % 4;
				}

				this.orientation = 0;
			}
		}
	}

	public void gauche() {
		if (x / Options.TAILLE_CELLULE != 0) {
			Cellule cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
			Cellule cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
			if (cell.libre && !(cell.entité instanceof Ennemi)) {
				if (cell.entité instanceof Personnage) {
					m_model.m_perso.m_mort = true;
				} else if (cell.entité instanceof Item) {
					m_item = (Item) cell.entité;
					m_item.possession = 2;
					m_item = null;
					cell.entité = null;
				} else if (cell.entité instanceof Bonus) {
					cell.entité.x = -100;
					cell.entité.y = -100;
					cell.entité = null;
				}
				cell.entité = this;
				cellActuel.entité = null;
				x -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 4 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 20 + (m_idx + 1) % 4;
				}
				this.orientation = 2;

			}
		}
	}

	public void animation() {

		// condition permettant la régulation de la vitesse du personnage
		if (m_cpt % 3 == 0) {

			if (m_idx == 3)
				m_idx = 0;
			if (m_idx == 7)
				m_idx = 4;
			if (m_idx == 11)
				m_idx = 8;
			if (m_idx == 15)
				m_idx = 12;

			m_idx++;

			if (orientation == 1 && x % Options.TAILLE_CELLULE != 0) {
				// 4 images par déplacement du personnage
				x += Options.TAILLE_CELLULE / 4;

			}

			if (orientation == 2 && x % Options.TAILLE_CELLULE != 0) {
				x -= Options.TAILLE_CELLULE / 4;

			}

			if (orientation == 3 && y % Options.TAILLE_CELLULE != 0) {
				y -= Options.TAILLE_CELLULE / 4;

			}

			if (orientation == 0 && y % Options.TAILLE_CELLULE != 0) {
				y += Options.TAILLE_CELLULE / 4;

			}
		}
	}

	public void Ennemi_Boss() {
		Ennemi_presence = true;
	}

	public void shoot() {
		if (Options.projectileBossLance == null) {
			int itemY = this.y;
			int itemX = this.x;
			System.out.println("EX: " + this.x + "EY: " + this.y);
			Item projectile = this.projectile;
			projectile.orientation = this.orientation;
			if (this.orientation == 0) {
				itemY = itemY + 60;
			} else if (this.orientation == 1) {
				itemX = itemX + 60;
			} else if (this.orientation == 2) {
				itemX = itemX - 60;
			} else if (this.orientation == 3) {
				itemY = itemY - 60;
			}
			projectile.x = itemX;
			projectile.y = itemY;
			projectile.hit = true;
			System.out.println("X:" + projectile.x + "    Y: " + projectile.y);
			Options.projectileBossLance = projectile;
		}
	}

	public void paint(Graphics g) {
		if (this.vie > 0) {
			g.drawRect(x, y, vie * 6, 5);
			g.setColor(Color.red);
			g.fillRect(x, y, vie * 6, 5);
			m_cpt++;
			Image img = m_sprites[m_idx];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, x - 10, y - 10, w, h, null);
		} else if (this.vie <= 0 && m_idxMort < 11) {
			m_cell.entité = null;
			Image img = m_spritesMort[m_idxMort];
			m_idxMort++;
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, x, y, w, h, null);
		}
	}

	@Override
	public void hit() {
		if (this.mort == false) {
			Cellule cell = new Cellule();
			switch (this.orientation) {
			case 0:
				cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
				break;
			case 1:
				cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
				break;
			case 2:
				cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
				break;
			case 3:
				cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
				break;
			}
			if (cell.entité instanceof Personnage) {
				if (((Personnage) cell.entité).invincible == false) {
					m_model.m_perso.m_mort = true;
					m_model.m_perso.p_vie--;
				}
			}
		}

	}

	@Override
	public void pick(String param) {
		Cellule cell;
		switch (param) {

		case "N":
			cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
			if (cell.entité instanceof Item) {
				m_item = (Item) cell.entité;
				m_item.possession = 2;
				cell.entité = null;
			} else {
				((Bonus) cell.entité).actionBonus(cell, this);
			}
			break;
		case "S":
			cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
			if (cell.entité instanceof Item) {
				m_item = (Item) cell.entité;
				m_item.possession = 2;
				cell.entité = null;
			} else {
				((Bonus) cell.entité).actionBonus(cell, this);
			}
			break;
		case "O":
			cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
			if (cell.entité instanceof Item) {
				m_item = (Item) cell.entité;
				m_item.possession = 2;
				cell.entité = null;
			} else {
				((Bonus) cell.entité).actionBonus(cell, this);
			}
			break;
		case "E":
			cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
			if (cell.entité instanceof Item) {
				m_item = (Item) cell.entité;
				m_item.possession = 2;
				cell.entité = null;
			} else {
				((Bonus) cell.entité).actionBonus(cell, this);
			}
			break;
		case "F":
			switch (this.orientation) {
			case 0:
				this.pick("S");
				break;
			case 3:
				this.pick("N");
				break;
			case 1:
				this.pick("E");
				break;
			case 2:
				this.pick("O");
				break;
			default:
				break;
			}
		default:
			break;
		}


	}

	@Override
	public void turn(String param) {
		// TODO Auto-generated method stub
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	public void follow() {

		int orientation = -1;
		int[] tab_j = m_model.m_perso.PosToCell();
		int[] tab_a = this.PosToCell();
		int a_x = tab_a[1];
		int a_y = tab_a[0];
		List<Grid2d.MapNode> route = m_model.map2d.findPath(tab_a[0], tab_a[1], tab_j[0], tab_j[1]);
		if (route != null) {

			// System.out.println(route.size());
			if (route.size() > 1) {
				Grid2d.MapNode element = route.get(1);

				int y_recherche = element.get_y();
				int x_recherche = element.get_x();
				int y_en = tab_a[1];
				int x_en = tab_a[0];
				int diff_x = x_en - x_recherche;
				int diff_y = y_en - y_recherche;
				// System.out.println(diff_x);
				// System.out.println(diff_y);
				if (diff_x == -1) {
					orientation = 0;
				} else if (diff_y == -1) {
					orientation = 1;
				} else if (diff_y == +1) {
					orientation = 2;
				} else if (diff_x == +1) {
					orientation = 3;
				}
				switch (orientation) {

				case 3:
					this.orientation = 3;
					if (m_model.m_carte.cellules[a_y - 1][a_x].entité instanceof Personnage) {
						if (!m_model.m_perso.m_mort)
							this.hit();
						this.wizz();
					} else if (!(m_model.m_carte.cellules[a_y - 1][a_x].entité instanceof Ennemi
							|| m_model.m_carte.cellules[a_y - 1][a_x].entité instanceof Obstacle)) {
						if (m_model.m_carte.cellules[a_y - 1][a_x].entité instanceof Item) {
							this.pick("N");
						}
						this.move("N");

					}
					break;
				case 0:
					this.orientation = 0;
					if (m_model.m_carte.cellules[a_y + 1][a_x].entité instanceof Personnage) {
						if (!m_model.m_perso.m_mort)
							this.hit();
						this.wizz();
					} else if (!(m_model.m_carte.cellules[a_y + 1][a_x].entité instanceof Ennemi
							|| m_model.m_carte.cellules[a_y + 1][a_x].entité instanceof Obstacle)) {
						if (m_model.m_carte.cellules[a_y + 1][a_x].entité instanceof Item) {
							this.pick("S");
						}
						this.move("S");

					}
					break;
				case 1:
					this.orientation = 1;
					if (m_model.m_carte.cellules[a_y][a_x + 1].entité instanceof Personnage) {
						if (!m_model.m_perso.m_mort)
							this.hit();
						this.wizz();
					} else if (!(m_model.m_carte.cellules[a_y][a_x + 1].entité instanceof Ennemi
							|| m_model.m_carte.cellules[a_y][a_x + 1].entité instanceof Obstacle)) {
						if (m_model.m_carte.cellules[a_y][a_x + 1].entité instanceof Ennemi) {
							this.pick("E");
						}

						this.move("E");

					}
					break;
				case 2:
					this.orientation = 2;
					if (m_model.m_carte.cellules[a_y][a_x - 1].entité instanceof Personnage) {
						if (!m_model.m_perso.m_mort)
							this.hit();
						this.wizz();
					} else if (!(m_model.m_carte.cellules[a_y][a_x - 1].entité instanceof Ennemi
							|| m_model.m_carte.cellules[a_y][a_x - 1].entité instanceof Obstacle)) {
						if (m_model.m_carte.cellules[a_y][a_x - 1].entité instanceof Ennemi) {
							this.pick("O");
						}

						this.move("O");

					}

					break;
				}
			}
		}
	}

	

	@Override
	public void threw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(String param) {
		Cellule cell;
		Cellule cellActuel;
		if (mort == false) {
			switch (param) {

			case "N":
				cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) - 1][x / Options.TAILLE_CELLULE];
				m_cell = cell;
				cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
				cell.entité = this;
				cellActuel.entité = null;
				y -= Options.TAILLE_CELLULE;
				if (m_item == null) {
					m_idx = 12 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 28 + (m_idx + 1) % 4;
				}

				this.orientation = 3;
				break;
			case "S":
				cell = m_model.m_carte.cellules[(y / Options.TAILLE_CELLULE) + 1][x / Options.TAILLE_CELLULE];
				m_cell = cell;
				cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
				cell.entité = this;
				cellActuel.entité = null;
				y += Options.TAILLE_CELLULE;
				if (m_item == null) {
					m_idx = (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 16 + (m_idx + 1) % 4;
				}

				this.orientation = 0;
				break;
			case "O":
				cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) - 1];
				m_cell = cell;
				cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
				cell.entité = this;
				cellActuel.entité = null;
				x -= Options.TAILLE_CELLULE;
				if (m_item == null) {
					m_idx = 4 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 20 + (m_idx + 1) % 4;
				}
				this.orientation = 2;
				break;
			case "E":
				cell = m_model.m_carte.cellules[y / Options.TAILLE_CELLULE][(x / Options.TAILLE_CELLULE) + 1];
				m_cell = cell;
				cellActuel = m_model.m_carte.cellules[y / 60][(x / 60)];
				cell.entité = this;
				cellActuel.entité = null;
				x += Options.TAILLE_CELLULE;
				if (m_item == null) {
					m_idx = 8 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 24 + (m_idx + 1) % 4;
				}
				this.orientation = 1;

				break;
			case "F":
				switch (this.orientation) {
				case 0:
					this.move("S");
					break;
				case 3:
					this.move("N");
					break;
				case 1:
					this.move("E");
					break;
				case 2:
					this.move("O");
					break;
				default:
					break;
				}
			default:
				break;
			}

		}

	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

}
