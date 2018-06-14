package automate;

import java.util.LinkedList;
import java.util.List;

import principal.Entity;
import ricm3.parser.Ast.Automaton;

public class _Automate {
	public Entity e;
	public _State courant;
	public List<_Behaviour> behaviours;

	public _Automate(_State courant, List<_Behaviour> behav) {
		this.courant = courant;
		this.behaviours = behav;
	}

	public _Automate(Automaton A, Entity e) {
		A.make(this);
		this.e = e;
	}

	public void step(long now) {
		LinkedList<_Transition> valide = new LinkedList<_Transition>();
		for (int i = 0; i < this.behaviours.size(); i++) {
			_Behaviour b = this.behaviours.get(i);
			if (this.courant.name.equals(b.source.name)) {
				for (int j = 0; j < b.transitions.size(); j++) {
					_Transition t = b.transitions.get(j);
					if (t.condition.eval(this.e)) {
						valide.add(t);
					}
				}
				int rand = (int) (Math.random() * (valide.size()));
				if (valide.size() != 0) {
					valide.get(rand).act.execute(this.e);
					this.courant.name = valide.get(rand).dest.name;
				}
			}
		}
	}
}
