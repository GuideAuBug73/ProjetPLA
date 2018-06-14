package automate;

import principal.Entity;

public class _Conjonction extends _Condition{
	
	public _Conjonction() {
		
	}
	@Override
	public boolean eval(Entity e) {
			return(cond1.eval(e) && cond2.eval(e));
	}

}
