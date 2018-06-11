package automate;

import principal.Entity;

public abstract class _Condition {
	String [] tab_parametre = new  String[2]; // entité à la case testé
     public abstract boolean eval(Entity e);
}
