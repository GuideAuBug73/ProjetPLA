package principal;

import basic.Cellule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus extends Entity {
    public int type;
    public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi
    public int compteurTickItem = 500;
    public boolean hit = false;
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
        Personnage personnage = null;
        if (entity instanceof Ennemi) {
            ennemi = (Ennemi) entity;
            if (this.type == 0) {
                ennemi.p_vie++;
            } else if (this.type == 1) {

            } else if (this.type == 2) {
                ennemi.invincible=true;
                Options.invincible=Options.time_vague+20;
            }
        } else if (entity instanceof Personnage) {
            personnage = (Personnage) entity;
            if (this.type == 0) {
                if (personnage.p_vie < 3) {
                    personnage.p_vie++;
                }
            } else if (this.type == 1) {
                Options.vitesse=2;
            } else if (this.type == 2) {
                personnage.invincible=true;
                Options.invincible=Options.time_vague+20;
            }
        }
    }

    public void timerInvincible(){
        if(Options.invincible!=0){
            if (Options.time_vague >= Options.invincible) {
                if (entity instanceof Personnage) {
                    Personnage perso=(Personnage)entity;
                    perso.invincible=false;
                }else if(entity instanceof Ennemi){
                    Ennemi ennemi=(Ennemi)entity;
                    ennemi.invincible=false;
                }
            }
        }
    }

    public void paint(Graphics g) {
        timerInvincible();
        g.drawImage(img, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }
}