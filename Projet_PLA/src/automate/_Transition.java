package automate;

public class _Transition {
	public _State dest;
	public _Condition condition;
	public _Action act;

	public _Transition(_State dest, _Condition cond, _Action act) {
		this.dest = dest;
		this.condition = cond;
		this.act = act;
	}
}
