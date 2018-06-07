package automate;

import principal.Entity;

public class Pop extends Action {

	public Pop() {
	}

	@Override
	public void execute(Entity e) {
		e.pop();

	}

}
