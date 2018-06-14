package automate;

import principal.Entity;

public abstract class _Action {
	public String [] tab_parametre = new  String[2]; // entité à la case testé
	public _Action action1;
	public _Action action2;
	public abstract void execute(Entity e);
}