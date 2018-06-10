package automate;

import java.util.List;

import ricm3.parser.Ast.Behaviour;

public class _Behaviour {
	public _State source;
	public List<_Transition> transitions;
	
	public _Behaviour(Behaviour B) {
		B.make(this);
	}
	
	

}