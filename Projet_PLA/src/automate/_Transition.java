package automate;

public class _Transition {
	_State dest;
	boolean condition;
	public _Action act;

	public _Transition(_State dest, boolean cond, _Action act) {
		this.dest = dest;
		this.condition = cond;
		this.act = act;
	}
}
