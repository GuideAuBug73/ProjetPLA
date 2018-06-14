package automate;

import principal.Entity;

public class _Negation extends _Condition {
	public _Condition cond;
	@Override
	public boolean eval(Entity e) {
		return (!cond.eval(e));
	}

}
