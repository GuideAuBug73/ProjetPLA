package automate;

import principal.Entity;

public class _Cell extends _Condition {
	public _Cell() {
	}

	@Override
	public void eval(Entity e) {
		e.cell_libre();
	}
}
