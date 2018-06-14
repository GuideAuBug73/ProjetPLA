package automate;

import principal.Entity;

public class _ConjonctionA extends _Action {
	
	public _ConjonctionA(){
		
	}
	
	@Override
	public void execute(Entity e) {
		action1.execute(e);
		action2.execute(e);
	}
	
	
}
