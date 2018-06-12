package automate;

import principal.Entity;

public class _Move extends _Action {

	public _Move() {

	}

	@Override
	public void execute(Entity e) {
		
		e.move(tab_parametre[0]);
	}

}
