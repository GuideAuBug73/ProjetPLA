package ricm3.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import automate.Joueur_in_range;
import automate.Joueur_Proche;
import automate.Presence;
import automate._Automate;
import automate._Behaviour;
import automate._Cell;
import automate._Hit;
import automate._Move;
import automate._Pick;
import automate._Pop;
import automate._State;
import automate._Transition;
import automate._Turn;
import automate._Wizz;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	/* All this is only for the graphical .dot output of the Abstract Syntax Tree */
	public String kind; /* the name of the non-terminal node */
	public int id = Id.fresh(); /* its unique id as a graph node */

	public String tree_edges() {
		return "undefined";
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.id, this.id) + as_dot_tree();
	}

	public String as_tree_node() {
		return Dot.non_terminal_node(this.id, this.kind);
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_dot_automata() {
		return "undefined";
	}

	public static abstract class Expression extends Ast {
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.value = string;
		}

		public String as_tree_son_of(Ast father) {
			return Dot.terminal_edge(father.id, value);
		}
	}

	public static class Constant extends Expression {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class Variable extends Expression {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}
	}

	public static class Direction extends Expression {

		Expression value;

		Direction(Expression expression) {
			this.kind = "Direction";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
	}

	public static class Entity extends Expression {

		Expression value;

		Entity(Expression expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
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
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Expression> parameters;

		FunCall(String name, List<Expression> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Expression> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Expression expression = Iter.next();
				output += expression.as_tree_son_of(this);
			}
			return output;
		}
		public void make (_Transition trans) {
			switch (name.toString()) {
			case "Cell":
				trans.condition = new _Cell();
				break;
			case "Move":
				trans.act = new _Move();
				break;
			case "Hit":
				trans.act = new _Hit();
				break;
			case "Wizz":
				trans.act = new _Wizz();
				break;
			case "Pop":
				trans.act = new _Pop();
				break;
			case "Pick":
				trans.act = new _Pick();
				break;
			case "GotStuff":
				trans.condition = new Joueur_in_range();
				break;
			case "GotPower":
				
				break;
			case "Closest":
				trans.condition = new Joueur_Proche();
				break;
			case "Key":
				
				break;
			case "MyDir":
				
				break;
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

		public void make(_Transition trans) {
			switch (expression.toString()) {
			case "Move":
				trans.act = new _Move();
				break;
			case "Pick":
				trans.act = new _Pick();
				break;
			case "Hit":
				trans.act = new _Hit();
				break;
			case "Wizz":
				trans.act = new _Wizz();
				break;
			case "Pop":
				trans.act = new _Pop();
				break;
			case "Turn":
				trans.act = new _Turn();
				break;
			}
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public void make(_Transition trans) {
			switch (expression.toString()) {
			case "Cell":
				trans.condition = new _Cell();
				break;
			case "Got_item":
				trans.condition = new Joueur_in_range();
				break;
			case "Joueur_proche":
				trans.condition = new Joueur_Proche();
				break;
			case "Presence":
				trans.condition = new Presence();
				break;
			}
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

		public void make(_State S) {
			S.name = this.name.toString();
		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

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

		public String as_dot_automata() {
			return Dot.graph("Automata", this.as_tree_node());
		}
	}

	public static class Automaton extends Ast {

		Terminal name;
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

		public void make(_Automate A) {
			A.courant = new _State(this.entry.name.toString());
			this.entry.make(A.courant);
			A.behaviours = new LinkedList<_Behaviour>();
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			ListIterator<_Behaviour> _Iter = A.behaviours.listIterator();
			while (Iter.hasNext() && _Iter.hasNext()) {
				Iter.next().make(_Iter.next());
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

		public void make(_Behaviour B) {
			B.source = new _State(this.source.name.toString());
			source.make(B.source);
			B.transitions = new LinkedList<_Transition>();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			ListIterator<_Transition> _Iter = B.transitions.listIterator();
			while (Iter.hasNext() && _Iter.hasNext()) {
				Iter.next().make(_Iter.next());
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

		public void make(_Transition T) {
			this.condition.make(T);
			this.action.make(T);
			this.target.make(T.dest);
		}
	}
}
