package automate;

import java.util.List;

import ricm3.parser.Ast.Behaviour;

public class _Behaviour {
	_State source;
	List<_Transition> transitions;
	
	public _Behaviour(Behaviour B) {
		B.make(this);
	}
	
	

}