package automate;

import principal.Entity;

public class Move extends Action{
	
	public Move() {
		
	}

	@Override
	public void execute(Entity e) {
		e.move();
	}

}
