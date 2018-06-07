package automate;

import principal.Entity;

public class Stack extends Action {

	public Stack() {
	}

	@Override
	public void execute(Entity e) {
		e.stack();

	}

}
