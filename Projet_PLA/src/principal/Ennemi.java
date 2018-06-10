package principal;

import basic.Cellule;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Ennemi extends IA {
	int m_w, m_h, m_w2, m_h2;
	int m_idx;
	int m_idxTransfo;
	float m_scale;
	BufferedImage[] m_sprites;
	BufferedImage[] m_spritesTransfo;
	int orientation;
	Cellule m_cell;
	Item m_item;
	int m_cpt;

	public Ennemi(Model model, BufferedImage sprite, BufferedImage spriteTransfo, int x, int y, float scale) {
		m_model = model;
		img = sprite;
		img2 = spriteTransfo;
		orientation = 0;
		m_item = null;
		this.x = x;
		this.y = y;
		m_scale = scale;
		m_cell = m_model.m_carte.cellules[y / 60][(x / 60)];
		if (m_cell.entité == null) {
			m_cell.entité = this;
		}
		m_cpt = 0;
		m_idxTransfo = -1;
		splitSprite();
	}

	public void splitSprite() {
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		m_sprites = new BufferedImage[4 * 8];
		m_w = width / 4;
		m_h = height / 8;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * 4) + j] = img.getSubimage(x, y, m_w, m_h);
			}
		}
		//sprite de transformation
		width = img2.getWidth(null);
		height = img2.getHeight(null);
		m_spritesTransfo = new BufferedImage[8 * 4];
		m_w2 = width / 8;
		m_h2 = height / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				int x = j * m_w2;
				int y = i * m_h2;
				m_spritesTransfo[(i * 8) + j] = img2.getSubimage(x, y, m_w2, m_h2);
			}
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
				}
				cell.entité = this;
				cellActuel.entité = null;
				x += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 8 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 24 + (m_idx + 1) % 4;
					//animation transformation
					m_idxTransfo++;
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
				}
				cell.entité = this;
				cellActuel.entité = null;
				y -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 12 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 28 + (m_idx + 1) % 4;
					m_idxTransfo++;
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
				}
				cell.entité = this;
				cellActuel.entité = null;
				y += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 16 + (m_idx + 1) % 4;
					m_idxTransfo++;
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
				}
				cell.entité = this;
				cellActuel.entité = null;
				x -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					m_idx = 4 + (m_idx + 1) % 4;
				} else if (m_item != null) {
					m_idx = 20 + (m_idx + 1) % 4;
					m_idxTransfo++;
				}
				this.orientation = 2;

			}
		}
	}

	public void animation() {
		// condition permettant la régulation de la vitesse du personnage
		if (m_cpt % 3 == 0) {
			if (orientation == 1 && (x - 4) % Options.TAILLE_CELLULE != 0) {
				// 4 images par déplacement du personnage
				x += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					// changement de sprite du personnage
					m_idx = 8 + (m_idx + 1) % 4;
				} else {
					m_idx = 24 + (m_idx + 1) % 4;
				}
			}

			if (orientation == 2 && (x - 4) % Options.TAILLE_CELLULE != 0) {
				x -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					// changement de sprite du personnage
					m_idx = 4 + (m_idx + 1) % 4;
				} else {
					m_idx = 20 + (m_idx + 1) % 4;
				}
			}

			if (orientation == 3 && (y - 13) % Options.TAILLE_CELLULE != 0) {
				y -= Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					// changement de sprite du personnage
					m_idx = 12 + (m_idx + 1) % 4;
				} else {
					m_idx = 28 + (m_idx + 1) % 4;
				}
			}

			if (orientation == 0 && (y - 13) % Options.TAILLE_CELLULE != 0) {
				y += Options.TAILLE_CELLULE / 4;
				if (m_item == null) {
					// changement de sprite du personnage
					m_idx = 0 + (m_idx + 1) % 4;
				} else {
					m_idx = 16 + (m_idx + 1) % 4;
				}
			}
		}
	}

	public void paint(Graphics g) {
		m_cpt++;
		Image img = null;
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		//Si l'ennemi se transforme
		if (m_idxTransfo >= 0 && m_idxTransfo < 32) {
			img = m_spritesTransfo[m_idxTransfo];
			m_idxTransfo++;
		//sinon
		} else if (m_model.m_perso.m_mort != true && m_idxTransfo < 0 || m_idxTransfo >= 32) {
			img = m_sprites[m_idx];
		}
		g.drawImage(img, x, y, w, h, null);
	}

}
