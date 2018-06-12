package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;
import basic.Orientation;

public class Item extends IA {
    public int type;
    public int possession;// 0 si non possédé 1 si c'est le joueur 2 si c'est l'ennemi
    public boolean casted = false;
    public int orientation;
    public int limit;
    public int compteurTickItem = 500;
    public boolean hit = false;


    public Item(int type, int w, int h, BufferedImage m_item, Model model) {
        this.type = type;
        m_model = model;
        x = w;
        y = h;
        img = m_item;
        if (this.type == 0 || this.type == 1) {
            limit = 40 * 30;
        } else if (this.type == 2 || this.type == 3) {
            limit = 6 * 30;
        } else if (this.type == 4 || this.type == 5) {
            limit = 6 * 30;
        } else if (this.type == 13) {
            limit = 2 * 30;
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
                if (this.type == 2 || this.type == 3) {
                    degatZone(this.y / 60, this.x / 60);
                }
                Options.itemlance.x = -100;
                Options.itemlance.y = -100;
                Options.itemlance = null;
                m_model.m_perso.projectile = new Item(13, -200, -200, m_model.m_spellSprite, m_model);

            }
        }
    }

    public void verifCellule() {
        int w = this.x / 60;
        int h = this.y / 60;
        try {
            if (m_model.m_carte.cellules[h][w].libre == false) {
                this.limit = 0;
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
            Personnage personnage = null;
            if (m_model.m_carte.cellules[h][w].entité instanceof Ennemi) {
                ennemi = (Ennemi) m_model.m_carte.cellules[h][w].entité;
            } else if (m_model.m_carte.cellules[h][w].entité instanceof Personnage) {
                personnage = (Personnage) m_model.m_carte.cellules[h][w].entité;
            }
            if (ennemi != null) {
                if (this.type == 0 || this.type == 1) {
                    ennemi.p_vie = ennemi.p_vie - 2;
                    System.out.println("Vie:"+ennemi.p_vie);
                    checkVie(ennemi);
                    hit = false;
                    this.limit=0;
                    System.out.println("Degat");
                } else if (this.type == 2 || this.type == 3) {
                    ennemi.p_vie = ennemi.p_vie - 2;
                    checkVie(ennemi);
                    degatZone(h, w);
                    hit = false;
                    this.limit=0;
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
                    this.limit=0;
                    System.out.println("Degat");
                }
            }
            if (personnage != null) {
                if (this.type == 0 || this.type == 1) {
                    personnage.p_vie = personnage.p_vie - 2;
                    checkVie(personnage);
                    this.limit=0;
                    hit = false;
                } else if (this.type == 2 || this.type == 3) {
                    ennemi.p_vie = ennemi.p_vie - 2;
                    checkVie(personnage);
                    this.limit=0;
                    degatZone(h, w);
                    hit = false;
                } else if (this.type == 4 || this.type == 5) {
                    personnage.p_vie = personnage.p_vie - 2;
                    checkVie(personnage);
                    hit = false;
                } else if (this.type == 13) {
                    personnage.p_vie = personnage.p_vie - 1;
                    checkVie(personnage);
                    this.limit=0;
                    hit = false;
                }
            }
        }
    }

    public void degatZone(int h, int w) {
        Ennemi ennemi = null;
        Personnage personnage = null;
        try {
            if (m_model.m_carte.cellules[h + 1][w].entité instanceof Ennemi) {
                ennemi = (Ennemi) m_model.m_carte.cellules[h + 1][w].entité;
                ennemi.p_vie = ennemi.p_vie - 2;
                checkVie(ennemi);
                System.out.println("Zone");
            }
            if (m_model.m_carte.cellules[h - 1][w].entité instanceof Ennemi) {
                ennemi = (Ennemi) m_model.m_carte.cellules[h - 1][w].entité;
                ennemi.p_vie = ennemi.p_vie - 2;
                checkVie(ennemi);
                System.out.println("Zone");
            }

            if (m_model.m_carte.cellules[h][w + 1].entité instanceof Ennemi) {
                ennemi = (Ennemi) m_model.m_carte.cellules[h][w + 1].entité;
                ennemi.p_vie = ennemi.p_vie - 2;
                checkVie(ennemi);
                System.out.println("Zone");
            }
            if (m_model.m_carte.cellules[h][w - 1].entité instanceof Ennemi) {
                ennemi = (Ennemi) m_model.m_carte.cellules[h][w - 1].entité;
                ennemi.p_vie = ennemi.p_vie - 2;
                checkVie(ennemi);
                System.out.println("Zone");
            }
            if (m_model.m_carte.cellules[h + 1][w].entité instanceof Personnage) {
                personnage = (Personnage) m_model.m_carte.cellules[h + 1][w].entité;
                personnage.p_vie = personnage.p_vie - 2;
                checkVie(personnage);
            }

            if (m_model.m_carte.cellules[h - 1][w].entité instanceof Personnage) {
                personnage = (Personnage) m_model.m_carte.cellules[h - 1][w].entité;
                personnage.p_vie = personnage.p_vie - 2;
                checkVie(personnage);
            }

            if (m_model.m_carte.cellules[h][w + 1].entité instanceof Personnage) {
                personnage = (Personnage) m_model.m_carte.cellules[h][w + 1].entité;
                personnage.p_vie = personnage.p_vie - 2;
                checkVie(personnage);

            }

            if (m_model.m_carte.cellules[h][w - 1].entité instanceof Personnage) {
                personnage = (Personnage) m_model.m_carte.cellules[h][w - 1].entité;
                personnage.p_vie = personnage.p_vie - 2;
                checkVie(personnage);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public void checkVie(Entity entity){
        Ennemi ennemi=null;
        Personnage personnage=null;
        if(entity instanceof Ennemi){
            ennemi=(Ennemi)entity;
            if(ennemi.p_vie<=0){
                ennemi.m_mort=true;
                ennemi.m_cell.entité=null;
            }
        }else{
            personnage=(Personnage)entity;
            if(personnage.p_vie<=0){
                personnage.m_mort=true;
            }
        }
    }


    public void paint(Graphics g) {
        if (possession == 0 || possession == 3)
            g.drawImage(img, x, y, Options.TAILLE_CELLULE, Options.TAILLE_CELLULE, null);
    }

    public void paint(Graphics g, int w, int h) {
        Image img = this.img;
        if (possession == 1)
            g.drawImage(img, w, h, Options.TAILLE_CELLULE - 20, Options.TAILLE_CELLULE - 20, null);
    }
}