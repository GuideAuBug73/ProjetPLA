package tests;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;

import org.junit.Test;

import basic.Cellule;
import principal.Entity;
import principal.Map;
import principal.Options;
import principal.Spawn;

public class MapTest {

	@Test
	public void test() {
		BufferedImage field = null;
		BufferedImage wall = null;
		Map carte = new Map(30,30,60,60,field,wall);
		for (int i = 0; i < 30; i++)
		{
			for(int j = 0; j < 30; j++) {
				System.out.println(carte.cellules[i][j].libre);
				System.out.println(carte.cellules[i][j].x);
				System.out.println(carte.cellules[i][j].y);
			}
		}
	}
	
	  @Test 
	  public void test2() { 
	 
	    BufferedImage field = null; 
	    BufferedImage wall = null; 
	    Map carte = new Map(Options.nb_cell_h,Options.nb_cell_w,Options.nb_px_y_max-Options.nb_px_y_min,Options.nb_px_x_max-Options.nb_px_x_min, wall, field); 
	    Entity e = null;   
	    Cellule start = new Cellule(e,true,500,500); 
	    Spawn s1 = new Spawn(800,650); 
	    Spawn s2 = new Spawn(250,200); 
	    Spawn s[] = {s1,s2}; 
	    if(!carte.testMap(s,start)) { 
	      fail("Erreur recherche stap"); 
	    } 
	  } 

}




