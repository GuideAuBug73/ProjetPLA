package automate;
import principal.Entity;

public class Presence extends _Condition {
	public Presence() {
	}

	@Override
	public void eval(Entity e) {
		e.presence();
	}
}