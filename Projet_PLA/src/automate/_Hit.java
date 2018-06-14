package automate;

import principal.Entity;

public class _Hit extends _Action{
	
	public _Hit() {
		
	}

	@Override
	public void execute(Entity e) {
		e.hit();
	}
}
