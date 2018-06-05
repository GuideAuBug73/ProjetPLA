package basic;

import principal.Entity;

public class Cellule {
    public Entity entité;
    public boolean libre;
    // coordonnées du centre d'une cellule
    public int x;
    public int y;

    public Cellule() {
    }

    public Cellule(Entity e, boolean l, int x, int y) {
        this.entité = e;
        this.libre = l;
        this.x = x;
        this.y = y;
    }

    int[] positionToCell(int x,int y){
        int[] cell=new int[2];
        cell[0]=x/60;
        cell[1]=y/60;
        return cell;
    }
}
