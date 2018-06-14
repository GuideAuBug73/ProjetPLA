package principal;

import basic.Cellule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Boss extends Entity {
	int m_w, m_h;
	int m_idx = 0, m_idxMort = 0;
	float m_scale;
	BufferedImage[] m_sprites, m_Expsprites;
	int orientation;
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
		orientation = 0;
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

	public void paint(Graphics g) {
		if (this.vie > 0) {
			g.drawRect(x-1, y-1, 10 * 6 +2, 5+2);
			g.setColor(Color.red);
			g.drawRect(x, y, vie * 6, 5);
			g.setColor(Color.red);
			g.fillRect(x, y, vie * 6, 5);
			m_cpt++;
			Image img = m_sprites[m_idx];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, x-10, y-10, w, h, null);
		}
		else if (this.vie <= 0 && m_idxMort < 11){
			m_cell.entité = null;
			Image img = m_spritesMort[m_idxMort];
			m_idxMort++;
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, x, y, w, h, null);
		}
	}

}
