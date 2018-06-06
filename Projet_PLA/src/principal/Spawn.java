package principal;

import basic.Cellule;

public class Spawn extends Entity {
	int presence;
	 int i, j; 
	 
	  public Spawn(Cellule c) { 
	    int ind[] = new int[2]; 
	    ind = PosToCell(c.x, c.y); 
	    this.i = ind[0]; 
	    this.j = ind[1]; 
	  } 
	   
	   
	  public Spawn(int x, int y) { 
	    int ind[] = new int[2]; 
	    ind = PosToCell(x,y); 
	    this.i = ind[0]; 
	    this.j = ind[1]; 
	  } 

}
