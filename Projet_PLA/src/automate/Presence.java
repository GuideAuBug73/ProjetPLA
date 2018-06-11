package automate;
import principal.Entity;

public class Presence extends _Condition {
	public Presence() {
	}

	@Override
	public boolean eval(Entity e) {
		return true;
	}
}