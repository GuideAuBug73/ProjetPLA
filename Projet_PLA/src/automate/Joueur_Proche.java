package automate;

import principal.Entity;

public class Joueur_Proche extends _Condition {
	int distance;

	public Joueur_Proche(int d) {
		this.distance = d;
	}

	@Override
	public boolean eval(Entity e) {
		e.joueur_proche();
	}
}