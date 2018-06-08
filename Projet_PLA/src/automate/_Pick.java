package automate;

import principal.Entity;

public class _Pick extends _Action {

	public _Pick() {
	}

	@Override
	public void execute(Entity e) {
		e.pick();

	}

}
