package automate;

import principal.Entity;

public class Hit extends Action{
	
	public Hit() {
		
	}

	@Override
	public void execute(Entity e) {
		e.hit();
	}
}
