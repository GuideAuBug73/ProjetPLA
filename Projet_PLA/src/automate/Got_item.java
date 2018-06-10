package automate;
import principal.Entity;

public class Got_item extends _Condition {
	public Got_item() {
	}

	@Override
	public void eval(Entity e) {
		e.got_item();
	}
}