package principal;
import java.awt.image.BufferedImage;
public class Entity {
	public int x,y;
	public BufferedImage img;
	public BufferedImage img2;

	public Model m_model;
	
	protected int[] PosToCell(int x, int y) { 
	    int i[] = new int[2]; 
	    i[0] = y/Options.TAILLE_CELLULE; 
	    i[1] = x/Options.TAILLE_CELLULE; 
	    return i; 
	  } 
}
