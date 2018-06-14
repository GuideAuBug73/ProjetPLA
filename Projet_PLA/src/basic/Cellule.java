package basic;

import principal.Entity;

public class Cellule {
    public Entity entité;
    public boolean libre;
    // coordonnées du centre d'une cellule
    public int x;
    public int y;
    public boolean visite = false;
    public int cout;
    public Cellule() {
    }

    public Cellule(Entity e, boolean l, int x, int y,int m_cout) {
        this.entité = e;
        this.libre = l;
        this.x = x;
        this.y = y;
        cout = m_cout;
    }
}
