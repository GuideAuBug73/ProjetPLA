package automate;

import principal.Entity;

public abstract class _Condition {
	
	public _Condition cond1;
	public _Condition cond2;
	public String [] tab_parametre = new  String[2]; // entité à la case testé
     public abstract boolean eval(Entity e);
}
