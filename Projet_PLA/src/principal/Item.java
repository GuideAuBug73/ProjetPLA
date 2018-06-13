package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;
import basic.Orientation;

public class Item extends Entity {
	public int type;
	public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi
	public boolean casted = false;
	public int orientation;
	public int limit;
	public int compteurTickItem = 500;
	public boolean hit = false;
	BufferedImage[] m_sprites;
	BufferedImage[] m_spritesExplo;
	int m_w, m_h, m_w2, m_h2;
	int m_idx;
	boolean m_zone;
	int m_idxExplo;
	int m_posDegatH;
	int m_posDegatW;

	public Item(int type, int w, int h, BufferedImage m_item, BufferedImage m_explo, Model model) {
		this.type = type;
		m_model = model;
		x = w;
		y = h;
		img = m_item;
		img2 = m_explo;
		if (this.type == 0 || this.type == 1) {
			limit = 40 * 30;
		} else if (this.type == 2 || this.type == 3) {
			limit = 6 * 30;
		} else if (this.type == 4 || this.type == 5) {
			limit = 6 * 30;
		} else if (this.type == 13) {
			limit = 2 * 30;
		}
		m_idx = 0;
		m_zone = false;
		m_idxExplo = 0;
		splitSprite();
	}

	public void splitSprite() {
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		m_sprites = new BufferedImage[2 * 2];
		m_w = width / 2;
		m_h = height / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * 2) + j] = img.getSubimage(x, y, m_w, m_h);
			}
		}

		width = img2.getWidth(null);
		height = img2.getHeight(null);
		m_spritesExplo = new BufferedImage[4 * 4];
		m_w2 = width / 4;
		m_h2 = height / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = j * m_w2;
				int y = i * m_h2;
				m_spritesExplo[(i * 4) + j] = img2.getSubimage(x, y, m_w2, m_h2);
			}
		}
	}

	public void setcast(int x, int y, int orientation) {
		casted = true;
		possession = 3;
		this.orientation = orientation;
		this.x = x;
		this.y = y;

		if (orientation == 0) {
			this.y += 2;
		} else if (orientation == 1) {
			this.x += 2;
		} else if (orientation == 2) {
			this.x -= 2;
		} else if (orientation == 3) {
			this.y -= 2;
		}
	}

	public void lanceItem() {
		if (compteurTickItem < 1) {
			compteurTickItem++;
		} else {
			verifCellule();
			if (Options.itemlance.limit != 0) {
				Options.itemlance.setcast(Options.itemlance.x, Options.itemlance.y, Options.itemlance.orientation);
				compteurTickItem = 0;
				Options.itemlance.limit--;
			} else {
				if (type == 2 || type == 3)
					m_zone = true;
				if (this.type == 2 || this.type == 3) {
					degatZone(this.y / 60, this.x / 60);
				}
				Options.itemlance.x = -100;
				Options.itemlance.y = -100;
				Options.itemlance = null;
				m_model.m_perso.projectile = new Item(13, -200, -200, m_model.m_spellSprite, m_model.m_exploSprite,
						m_model);

			}
		}
	}

	public void verifCellule() {
        int w = this.x / 60;
        int h = this.y / 60;
        m_posDegatH = h;
        m_posDegatW = w;
        try {
            if (m_model.m_carte.cellules[h][w].libre == false) {
        		m_model.m_carte.cellules[h][w].libre=true;
                this.limit = 0;
                if(type == 2 || type == 3) {
                	m_zone = true;
                }
            }
            if (m_model.m_carte.cellules[h][w].entité instanceof Entity) {
                degat(h, w);
            }
        } catch (Exception e) {

        }
    }

	public void degat(int h, int w) {
		if (hit == true) {
			Ennemi ennemi = null;
			Joueur personnage = null;
			if (m_model.m_carte.cellules[h][w].entité instanceof Ennemi) {
				ennemi = (Ennemi) m_model.m_carte.cellules[h][w].entité;
			} else if (m_model.m_carte.cellules[h][w].entité instanceof Joueur) {
				personnage = (Joueur) m_model.m_carte.cellules[h][w].entité;
			}
			if (ennemi != null) {
				if (this.type == 0 || this.type == 1) {
					ennemi.p_vie = ennemi.p_vie - 2;
					System.out.println("Vie:" + ennemi.p_vie);
					checkVie(ennemi);
					hit = false;
					this.limit = 0;
					System.out.println("Degat");
				} else if (this.type == 2 || this.type == 3) {
					ennemi.p_vie = ennemi.p_vie - 2;
					checkVie(ennemi);
					degatZone(h, w);
					m_zone = true;
					hit = false;
					this.limit = 0;
					System.out.println("Degat");
				} else if (this.type == 4 || this.type == 5) {
					ennemi.p_vie = ennemi.p_vie - 2;
					checkVie(ennemi);
					hit = false;
					System.out.println("Degat");
				} else if (this.type == 13) {
					ennemi.p_vie = ennemi.p_vie - 1;
					checkVie(ennemi);
					hit = false;
					this.limit = 0;
					System.out.println("Degat");
				}
			}
			if (personnage != null) {
				if (this.type == 0 || this.type == 1) {
					personnage.p_vie = personnage.p_vie - 1;
					personnage.m_mort = true;
					checkVie(personnage);
					this.limit = 0;
					hit = false;
				} else if (this.type == 2 || this.type == 3) {
					ennemi.p_vie = ennemi.p_vie - 1;
					personnage.m_mort = true;
					checkVie(personnage);
					this.limit = 0;
					degatZone(h, w);
					hit = false;
				} else if (this.type == 4 || this.type == 5) {
					personnage.p_vie = personnage.p_vie - 1;
					personnage.m_mort = true;
					checkVie(personnage);
					hit = false;
				} else if (this.type == 13) {
					personnage.p_vie = personnage.p_vie - 1;
					personnage.m_mort = true;
					checkVie(personnage);
					this.limit = 0;
					hit = false;
				}
			}
		}
	}

	public void degatZone(int h, int w) {
		Ennemi ennemi = null;
		Joueur personnage = null;
		try {
			if (m_model.m_carte.cellules[h + 1][w].libre == false) {
				m_model.m_carte.cellules[h + 1][w].libre = true;
			}
			if (m_model.m_carte.cellules[h + 1][w].entité instanceof Ennemi) {
				ennemi = (Ennemi) m_model.m_carte.cellules[h + 1][w].entité;
				ennemi.p_vie = ennemi.p_vie - 2;
				checkVie(ennemi);
				System.out.println("Zone");
			}
			if (m_model.m_carte.cellules[h - 1][w].libre == false) {
				m_model.m_carte.cellules[h - 1][w].libre = true;
			}
			if (m_model.m_carte.cellules[h - 1][w].entité instanceof Ennemi) {
				ennemi = (Ennemi) m_model.m_carte.cellules[h - 1][w].entité;
				ennemi.p_vie = ennemi.p_vie - 2;
				checkVie(ennemi);
				System.out.println("Zone");
			}
			if (m_model.m_carte.cellules[h][w + 1].libre == false) {
				m_model.m_carte.cellules[h][w + 1].libre = true;
			}
			if (m_model.m_carte.cellules[h][w + 1].entité instanceof Ennemi) {
				ennemi = (Ennemi) m_model.m_carte.cellules[h][w + 1].entité;
				ennemi.p_vie = ennemi.p_vie - 2;
				checkVie(ennemi);
				System.out.println("Zone");
			}
			if (m_model.m_carte.cellules[h][w - 1].libre == false) {
				m_model.m_carte.cellules[h][w - 1].libre = true;
			}
			if (m_model.m_carte.cellules[h][w - 1].entité instanceof Ennemi) {
				ennemi = (Ennemi) m_model.m_carte.cellules[h][w - 1].entité;
				ennemi.p_vie = ennemi.p_vie - 2;
				checkVie(ennemi);
				System.out.println("Zone");
			}

			if (m_model.m_carte.cellules[h + 1][w].entité instanceof Joueur) {
				personnage = (Joueur) m_model.m_carte.cellules[h + 1][w].entité;
				personnage.p_vie = personnage.p_vie - 1;
				personnage.m_mort = true;
				checkVie(personnage);
			}

			if (m_model.m_carte.cellules[h - 1][w].entité instanceof Joueur) {
				personnage = (Joueur) m_model.m_carte.cellules[h - 1][w].entité;
				personnage.p_vie = personnage.p_vie - 1;
				personnage.m_mort = true;
				checkVie(personnage);
			}

			if (m_model.m_carte.cellules[h][w + 1].entité instanceof Joueur) {
				personnage = (Joueur) m_model.m_carte.cellules[h][w + 1].entité;
				personnage.p_vie = personnage.p_vie - 1;
				personnage.m_mort = true;
				checkVie(personnage);

			}

			if (m_model.m_carte.cellules[h][w - 1].entité instanceof Joueur) {
				personnage = (Joueur) m_model.m_carte.cellules[h][w - 1].entité;
				personnage.p_vie = personnage.p_vie - 1;
				personnage.m_mort = true;
				checkVie(personnage);
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	public void checkVie(Entity entity) {
		Ennemi ennemi = null;
		Joueur personnage = null;
		if (entity instanceof Ennemi) {
			ennemi = (Ennemi) entity;
			if (ennemi.p_vie <= 0) {
				ennemi.m_mort = true;
				ennemi.m_cell.entité = null;
			}
		} else {
			personnage = (Joueur) entity;
			if (personnage.p_vie <= 0) {
				personnage.m_mort = true;
			}
		}
	}

	public void paint(Graphics g) {
		Image img = null;
		// possession 3 = jeté
		// possession 1 = inventaire
		// possession 2 = ennemi
		if (possession == 3) {
			m_idx = (m_idx + 1) % 4;
			img = m_sprites[m_idx];
		}
		// possession 0 = au sol
		else if (possession == 0) {
			img = m_sprites[0];
		}
		g.drawImage(img, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
		//dessin explosion
		if (m_zone == true && m_idxExplo < 16) {
			System.out.println("x :" + m_posDegatW + "y " + m_posDegatH);
			img = m_spritesExplo[m_idxExplo];
			g.drawImage(img, m_posDegatW * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					m_posDegatH * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE, (int) ((Options.TAILLE_CELLULE) * 3),
					(int) ((Options.TAILLE_CELLULE) * 3), null);
			g.drawImage(img, (m_posDegatW + 1) * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					m_posDegatH * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE, (int) ((Options.TAILLE_CELLULE) * 3),
					(int) ((Options.TAILLE_CELLULE) * 3), null);
			g.drawImage(img, m_posDegatW * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					(m_posDegatH + 1) * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					(int) ((Options.TAILLE_CELLULE) * 3), (int) ((Options.TAILLE_CELLULE) * 3), null);
			g.drawImage(img, (m_posDegatW - 1) * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					m_posDegatH * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE, (int) ((Options.TAILLE_CELLULE) * 3),
					(int) ((Options.TAILLE_CELLULE) * 3), null);
			g.drawImage(img, (m_posDegatW) * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					(m_posDegatH - 1) * Options.TAILLE_CELLULE - Options.TAILLE_CELLULE,
					(int) ((Options.TAILLE_CELLULE) * 3), (int) ((Options.TAILLE_CELLULE) * 3), null);
			m_idxExplo++;
		}
	}

	public void paint(Graphics g, int w, int h) {
		Image img = this.img;
		if (possession == 1) {
			img = m_sprites[0];
			g.drawImage(img, w, h, Options.TAILLE_CELLULE - 20, Options.TAILLE_CELLULE - 20, null);

		}
	}
	
	public void move() {
	}
	public void hit() {
		
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