package automate;

import java.util.List;

import ricm3.parser.Ast.Automaton;

public class _Automate {
	public _State courant;
	public List<_Behaviour> behaviours;

	
	public _Automate(_State courant,List<_Behaviour> behav) {
		this.courant = courant;
		this.behaviours = behav;
	}
	
	public _Automate(Automaton A) {
		A.make(this);
	}
}
