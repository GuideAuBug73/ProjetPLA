package automate;

import principal.Entity;

public class Wizz extends Action {

	public Wizz() {
	}

	@Override
	public void execute(Entity e) {
		e.wizz();

	}

}
