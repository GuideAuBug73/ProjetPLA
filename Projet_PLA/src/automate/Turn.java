package automate;

import principal.Entity;

public class Turn extends Action {
	
	public Turn() {
		
	}

	@Override
	public void execute(Entity e) {
		e.turn();
		
	}

}
