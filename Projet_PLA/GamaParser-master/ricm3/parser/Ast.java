package ricm3.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import automate.Follow;
import automate.Joueur_Proche;
import automate._Action;
import automate._Automate;
import automate._Behaviour;
import automate._Cell;
import automate._Condition;
import automate._Conjonction;
import automate._ConjonctionA;
import automate._Disjunction;
import automate._DisjunctionA;
import automate._Hit;
import automate._Move;
import automate._Pick;
import automate._Pop;
import automate._State;
import automate._Throw;
import automate._Transition;
import automate._True;
import automate._Turn;
import automate._Wizz;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	// All this is only for the graphical .dot output of the Abstract Syntax Tree

	public String kind; // the name of the non-terminal node

	public int id = Id.fresh(); // a unique id used as a graph node

	// AST as tree

	public String dot_id() {
		return Dot.node_id(this.id);
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree();
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}

	public String tree_edges() {
		return "undefined: " + this.kind + ".tree_edges";
	}

	// AST as automata in .dot format

	public String as_dot_aut() {
		return "undefined " + this.kind + ".as_dot_aut";
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal";
			this.value = string;
		}

		public String toString() {
			return value;
		}

		public String tree_edges() {
			String value_id = Dot.node_id(-this.id);
			return Dot.declare_node(value_id, value, "shape=none, fontsize=10, fontcolor=blue")
					+ Dot.edge(this.dot_id(), value_id);
		}
	}

	// Value = Constant U Variable

	public static abstract class Value extends Ast {
		public abstract void make(_Action A);

		public abstract void make(_Condition C);
	}

	public static class Constant extends Value {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		@Override
		public void make(_Action A) {
			// TODO Auto-generated method stub

		}

		@Override
		public void make(_Condition C) {
			// TODO Auto-generated method stub

		}
	}

	public static class Variable extends Value {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String toString() {
			return name.toString();
		}

		@Override
		public void make(_Action A) {
			// TODO Auto-generated method stub

		}

		@Override
		public void make(_Condition C) {
			// TODO Auto-generated method stub

		}
	}

	// Parameter = Underscore U Key U Direction U Entity
	// Parameter are not Expression (no recursion)

	public static abstract class Parameter extends Ast {
		public abstract void make(_Action A);

		public abstract void make(_Condition C);
	}

	public static class Underscore extends Parameter {
		Underscore() {
			this.kind = "Underscore";
		}

		public String tree_edges() {
			return "";
		}

		public String toString() {
			return "_";
		}

		@Override
		public void make(_Action A) {
			// TODO Auto-generated method stub

		}

		@Override
		public void make(_Condition C) {
			// TODO Auto-generated method stub

		}
	}

	public static class Key extends Parameter {

		Constant value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		@Override
		public void make(_Action A) {
			if (A.tab_parametre[0] == null) {
				A.tab_parametre[0] = value.toString();
			} else {
				A.tab_parametre[1] = value.toString();
			}

		}

		@Override
		public void make(_Condition C) {
			if (C.tab_parametre[0] == null) {
				C.tab_parametre[0] = value.toString();
			} else {
				C.tab_parametre[1] = value.toString();
			}

		}
	}

	public static class Direction extends Parameter {

		Value value;

		Direction(Value value) {
			this.kind = "Direction";
			this.value = value;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		@Override
		public void make(_Action A) {
			if (A.tab_parametre[0] == null) {
				A.tab_parametre[0] = value.toString();
			} else {
				A.tab_parametre[1] = value.toString();
			}
		}

		@Override
		public void make(_Condition C) {
			if (C.tab_parametre[0] == null) {
				C.tab_parametre[0] = value.toString();
			} else {
				C.tab_parametre[1] = value.toString();
			}
		}
	}

	public static class Entity extends Parameter {

		Value value;

		Entity(Value expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		@Override
		public void make(_Action A) {
			if (A.tab_parametre[0] == null) {
				A.tab_parametre[0] = value.toString();
			} else {
				A.tab_parametre[1] = value.toString();
			}

		}

		@Override
		public void make(_Condition C) {
			if (C.tab_parametre[0] == null) {
				C.tab_parametre[0] = value.toString();
			} else {
				C.tab_parametre[1] = value.toString();
			}

		}
	}

	public static abstract class Expression extends Ast {
		public abstract String toString();

		public abstract void make(_Transition trans, String kind);

		public void make(_Condition condition, String kind) {
			// TODO Auto-generated method stub

		}

		public void make(_Action act, String kind) {
			// TODO Auto-generated method stub

		}
	}

	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}

		public String toString() {
			return operator + "(" + operand + ")";
		}

		@Override
		public void make(_Transition trans, String kind) {
			// TODO Auto-generated method stub

		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}

		public String toString() {
			return "(" + left_operand + " " + operator + " " + right_operand + ")";
		}

		@Override
		public void make(_Transition trans, String kind) {
			if (operator.value == "/") {
				if (kind == "Condition") {
					trans.condition = new _Disjunction();
					left_operand.make(trans.condition, "left");
					right_operand.make(trans.condition, "right");
				} else {
					trans.act = new _DisjunctionA();
					left_operand.make(trans.act, "left");
					right_operand.make(trans.act, "right");
				}
			}

			else {
				if (kind == "Condition") {
					trans.condition = new _Conjonction();
					left_operand.make(trans.condition, "left");
					right_operand.make(trans.condition, "right");
				} else {
					trans.act = new _ConjonctionA();
					left_operand.make(trans.act, "left");
					right_operand.make(trans.act, "right");
				}
			}

		}

		public void make(_Action action, String kind) {
			if (operator.value == "/") {
				if (kind.equals("left")) {
					action.action1 = new _DisjunctionA();
					left_operand.make(action.action1, "left");
					right_operand.make(action.action1, "right");
				} else {
					action.action2 = new _DisjunctionA();
					left_operand.make(action.action2, "left");
					right_operand.make(action.action2, "right");
				}
			}

			else {
				if (kind.equals("left")) {
					action.action1 = new _ConjonctionA();
					left_operand.make(action.action1, "left");
					right_operand.make(action.action1, "right");
				} else {
					action.action2 = new _ConjonctionA();
					left_operand.make(action.action2, "left");
					right_operand.make(action.action2, "right");
				}
			}
		}

		public void make(_Condition cond, String kind) {
			if (operator.value == "/") {
				if (kind.equals("left")) {
					cond.cond1 = new _Disjunction();
					left_operand.make(cond.cond1, "left");
					right_operand.make(cond.cond1, "right");
				} else {
					cond.cond2 = new _Disjunction();
					left_operand.make(cond.cond2, "left");
					right_operand.make(cond.cond2, "right");
				}
			}

			else {
				if (kind.equals("left")) {
					cond.cond1 = new _Conjonction();
					left_operand.make(cond.cond1, "left");
					right_operand.make(cond.cond1, "right");
				} else {
					cond.cond2 = new _Conjonction();
					left_operand.make(cond.cond2, "left");
					right_operand.make(cond.cond2, "right");
				}
			}
		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Parameter> parameters;

		FunCall(String name, List<Parameter> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				output += parameter.as_tree_son_of(this);
			}
			return output;
		}

		public String toString() {
			String string = new String();
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				string += parameter.toString();
				if (Iter.hasNext()) {
					string += ",";
				}
			}
			return name + "(" + string + ")";
		}

		public void make(_Transition trans, String kind) {
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			switch (name.toString()) {
			case "Cell":
				trans.condition = new _Cell();
				while (Iter.hasNext()) {
					Iter.next().make(trans.condition);
				}
				break;
			case "Move":
				trans.act = new _Move();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
				
			case "Follow":
				trans.act = new Follow();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Hit":
				trans.act = new _Hit();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Wizz":
				trans.act = new _Wizz();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Pop":
				trans.act = new _Pop();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Pick":
				trans.act = new _Pick();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Turn":
				trans.act = new _Turn();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "Throw":
				trans.act = new _Throw();
				while (Iter.hasNext()) {
					Iter.next().make(trans.act);
				}
				break;
			case "GotPower":

				break;
			case "Closest":
				trans.condition = new Joueur_Proche();
				while (Iter.hasNext()) {
					Iter.next().make(trans.condition);
				}
				break;
			case "True":
				trans.condition = new _True();
				while (Iter.hasNext()) {
					Iter.next().make(trans.condition);
				}
				break;
			case "MyDir":

				break;
			}
		}

		public void make(_Action act, String kind) {
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			if (kind.equals("left")) {
				switch (name.toString()) {
				case "Move":
					act.action1 = new _Move();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Hit":
					act.action1 = new _Hit();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Wizz":
					act.action1 = new _Wizz();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Pop":
					act.action1 = new _Pop();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Pick":
					act.action1 = new _Pick();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Turn":
					act.action1 = new _Turn();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				case "Throw":
					act.action1 = new _Throw();
					while (Iter.hasNext()) {
						Iter.next().make(act.action1);
					}
					break;
				}
			} else {
				switch (name.toString()) {
				case "Move":
					act.action2 = new _Move();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Hit":
					act.action2 = new _Hit();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Wizz":
					act.action2 = new _Wizz();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Pop":
					act.action2 = new _Pop();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Pick":
					act.action2 = new _Pick();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Turn":
					act.action2 = new _Turn();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				case "Throw":
					act.action2 = new _Throw();
					while (Iter.hasNext()) {
						Iter.next().make(act.action2);
					}
					break;
				}
			}
		}

		public void make(_Condition cond, String kind) {
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			if (kind.equals("left")) {
				switch (name.toString()) {
				case "Cell":
					cond.cond1 = new _Cell();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond1);
					}
					break;
				case "Closest":
					cond.cond1 = new Joueur_Proche();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond1);
					}
					break;
				case "True":
					cond.cond1 = new _True();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond1);
					}
					break;
				}
			} else {
				switch (name.toString()) {
				case "Cell":
					cond.cond2 = new _Cell();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond2);
					}
					break;
				case "Closest":
					cond.cond2 = new Joueur_Proche();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond2);
					}
					break;
				case "True":
					cond.cond2 = new _True();
					while (Iter.hasNext()) {
						Iter.next().make(cond.cond2);
					}
					break;
				}

			}
		}
	}

	public static class Action extends Ast {

		Expression expression;

		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public String toString() {
			return expression.toString();
		}

		public void make(_Transition trans) {
			expression.make(trans, this.kind);
		}
	}

	public static class Condition extends Ast {
		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public String toString() {
			return expression.toString();
		}

		public void make(_Transition trans) {
			expression.make(trans, this.kind);
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String dot_id_of_state_of(Automaton automaton) {
			return Dot.name(automaton.id + "." + name.toString());
		}

		public String as_state_of(Automaton automaton) {
			return Dot.declare_node(this.dot_id_of_state_of(automaton), name.toString(), "shape=circle, fontsize=4");
		}
	}

	public static class AI_Definitions extends Ast {

		public List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_aut() {
			String string = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				string += automaton.as_dot_aut();
			}
			return Dot.graph("Automata", string);
		}
	}

	public static class Automaton extends Ast {

		public Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_aut() {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), name.toString(), "shape=box, fontcolor=red");
			string += Dot.edge(this.dot_id(), entry.dot_id_of_state_of(this));
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				string += behaviour.as_transition_of(this);
			}
			return Dot.subgraph(this.id, string);
		}

		public void make(_Automate A) {
			A.courant = new _State(this.entry.name.value);
			A.behaviours = new LinkedList<_Behaviour>();
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			int i = 0;
			while (Iter.hasNext()) {
				_Behaviour b = new _Behaviour();
				A.behaviours.add(b);
				Iter.next().make(A.behaviours.get(i));
				i++;
			}
		}
	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}

		public String as_transition_of(Automaton automaton) {
			String string = new String();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				string += transition.as_transition_from(automaton, source);
			}
			return source.as_state_of(automaton) + string;
		}

		public void make(_Behaviour B) {
			B.source = new _State(this.source.name.value);
			B.transitions = new LinkedList<_Transition>();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			int i = 0;
			while (Iter.hasNext()) {
				_Transition t = new _Transition();
				B.transitions.add(t);
				Iter.next().make(B.transitions.get(i));
				i++;
			}
		}
	}

	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}

		public String toString() {
			return condition + "? " + action;
		}

		public String as_transition_from(Automaton automaton, State source) {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), this.toString(), "shape=box, fontcolor=blue, fontsize=6");
			string += Dot.edge(source.dot_id_of_state_of(automaton), this.dot_id());
			string += Dot.edge(this.dot_id(), target.dot_id_of_state_of(automaton));
			return string;
		}

		public void make(_Transition T) {
			T.dest = new _State(target.name.toString());
			this.condition.make(T);
			this.action.make(T);
		}
	}
}
