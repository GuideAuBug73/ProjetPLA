package automate;

public class Transition {
	State src;
	State dest;
	boolean condition;
	public Action act;

	public Transition(State src, State dest, boolean cond, Action act) {
		this.src = src;
		this.dest = dest;
		this.condition = cond;
		this.act = act;
	}
}
