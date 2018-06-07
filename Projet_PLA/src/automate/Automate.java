package automate;

public class Automate {
	int courant;
	public Transition[] trans;
	int etat_arret;
	
	public Automate(int courant,Transition tab[],int arret) {
		this.courant = courant;
		this.trans = tab;
		this.etat_arret = arret;
	}
}
