package automate;
import principal.Entity;

public class Joueur_Proche extends _Condition {
	public Joueur_Proche() {
	}

	@Override
	public void eval(Entity e) {
		e.joueur_proche();
	}
}