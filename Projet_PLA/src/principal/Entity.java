package principal;
import java.awt.image.BufferedImage;
public abstract class Entity {
	public int x,y;
	public BufferedImage img;
	public Model m_model;
	
	public int[] PosToCell(int x, int y) { 
	    int i[] = new int[2]; 
	    i[0] = y/Options.TAILLE_CELLULE; 
	    i[1] = x/Options.TAILLE_CELLULE; 
	    return i; 
	  } 
	
	public abstract void move();
	public abstract void hit();
	public abstract void pick();
	public abstract void turn();
	public abstract void wizz();
	public abstract void pop();

}
