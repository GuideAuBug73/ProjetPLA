package automate;
import java.util.LinkedList;

import principal.Entity;

public class _DisjunctionA extends _Action {
	public LinkedList<_Action> a;
	
	@Override
	public void execute(Entity e) {
		int rand = (int)(Math.random()*a.size()-1);
		a.get(rand).execute(e);
	}
	
	
}
