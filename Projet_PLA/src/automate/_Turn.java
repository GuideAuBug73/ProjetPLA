package automate;

import principal.Entity;

public class _Turn extends _Action {
	
	public _Turn() {
		
	}

	@Override
	public void execute(Entity e) {
		e.turn(tab_parametre[0]);
		
	}

}
