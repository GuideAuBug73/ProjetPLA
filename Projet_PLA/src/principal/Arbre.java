package principal;

import basic.Cellule;

public class Arbre {
	Cellule c;
	Arbre filsN;
	Arbre filsE;
	Arbre filsS;
	Arbre filsO;
	
	public Arbre(Cellule start) {
		this.c = start;
	}
	
	public Arbre(Spawn start,Map carte) {
		this.c = carte.cellules[start.i][start.j];
	}
}
