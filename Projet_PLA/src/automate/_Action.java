package automate;

import principal.Entity;

public abstract class _Action {
	String [] tab_parametre = new  String[2]; // entité à la case testé
	
	public abstract void execute(Entity e);
}