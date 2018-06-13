package automate;

import principal.Entity;

public class Follow extends _Action {

	public Follow() {
	}

	@Override
	public void execute(Entity e) {
		e.follow();

	}

}
