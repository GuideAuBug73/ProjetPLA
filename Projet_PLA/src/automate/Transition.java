package automate;

public class Transition {
	  int src; 
	  int dest; 
	  boolean condition; 
	  public Action act; 
	  
	  public Transition(int src,int dest, boolean cond, Action act) {
		  this.src = src;
		  this.dest = dest;
		  this.condition = cond;
		  this.act = act;
	  }
}
