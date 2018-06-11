package automate;
import principal.Entity;

public class Got_item extends _Condition {
	public Got_item() {
	}

	@Override
	public boolean eval(Entity e) {
		e.got_item();
	}
}