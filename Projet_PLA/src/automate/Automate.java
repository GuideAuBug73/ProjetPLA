package automate;

import java.util.List;

import ricm3.parser.Ast.Behaviour;

public class Automate {
	State courant;
	List<Behaviour> behaviours;

	
	public Automate(State courant,List<Behaviour> behav) {
		this.courant = courant;
		this.behaviours = behav;
	}
}
