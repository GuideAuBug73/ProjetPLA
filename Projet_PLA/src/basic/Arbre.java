package basic;

public class Arbre {
	public Cellule c;
	public Arbre filsN;
	public Arbre filsE;
	public Arbre filsS;
	public Arbre filsO;
	
	public Arbre(Cellule start) {
		this.c = start;
		this.filsN = null;
		this.filsE = null;
		this.filsS = null;
		this.filsO = null;
	}
}
