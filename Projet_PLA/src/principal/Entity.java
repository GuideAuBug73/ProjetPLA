package principal;
import java.awt.image.BufferedImage;

import basic.Position;
import java.awt.image.BufferedImage;
public class Entity {
	public Position p;
	BufferedImage Img;
	
	protected int[] PosToCell(int x, int y) { 
	    int i[] = new int[2]; 
	    i[0] = y/60; 
	    i[1] = x/60; 
	    return i; 
	  } 
}
