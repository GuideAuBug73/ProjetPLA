package automate;
import principal.Entity;

public class _DisjunctionA extends _Action {
	public _DisjunctionA() {
		
	}
	
	@Override
	public void execute(Entity e) {
		int rand = (int)(Math.random()*2);
		if(rand == 0) {
			this.action1.execute(e);
		}else {
			this.action2.execute(e);
		}
	}
	
	
}
