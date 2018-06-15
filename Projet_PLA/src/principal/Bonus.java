package principal;

import basic.Cellule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus extends Entity {
    public int type;
    Entity entity;


    public Bonus(int type, int x, int y, BufferedImage m_item, Model model) {
        this.type = type;
        m_model = model;
        this.x = x;
        this.y = y;
        img = m_item;
    }

    public void actionBonus(Cellule cell, Entity entity) {
        cell.entité = null;
        this.x = -200;
        this.y = -200;
        Ennemi ennemi = null;
        this.entity = entity;
        Personnage personnage = null;
        if (entity instanceof Ennemi) {
            ennemi = (Ennemi) entity;
            if (this.type == 0) {
                ennemi.p_vie++;
            } else if (this.type == 1) {
                Options.vitesseEnnemie = 2;
                Options.timerVitesse = Options.time_vague+40;
            } else if (this.type == 2) {
                ennemi.invincible = true;
                Options.invincible = Options.time_vague + 40;
            }
        } else if (entity instanceof Personnage) {
            personnage = (Personnage) entity;
            if (this.type == 0) {
                if (personnage.p_vie < 3) {
                    personnage.p_vie++;
                }
            } else if (this.type == 1) {
                Options.vitesse = 2;
                Options.timerVitesse = Options.time_vague+40;
            } else if (this.type == 2) {
                personnage.invincible = true;
                Options.invincible = Options.time_vague + 40;
            }
        }
    }

    public void timerInvincible() {
        if (Options.invincible != 0) {
            if (Options.time_vague >= Options.invincible) {
                if (entity instanceof Personnage) {
                    Personnage perso = (Personnage) entity;
                    perso.invincible = false;
                } else if (entity instanceof Ennemi) {
                    Ennemi ennemi = (Ennemi) entity;
                    ennemi.invincible = false;
                }
            }
        }
        if (Options.vitesse == 2) {
            if (Options.time_vague >= Options.timerVitesse) {
                if (entity instanceof Personnage) {
                    Options.vitesse = 4;
                } else if (entity instanceof Ennemi) {
                    Options.vitesseEnnemie = 4;
                }
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, x+10, y+10, Options.TAILLE_CELLULE-20, Options.TAILLE_CELLULE-20, null);
        timerInvincible();
    }

	@Override
	public void move(String param) {
		// TODO Auto-generated method stub
		
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
	public void follow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void threw() {
		// TODO Auto-generated method stub
		
	}
}