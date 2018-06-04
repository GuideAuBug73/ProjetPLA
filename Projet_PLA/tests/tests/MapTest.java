package tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

import basic.Cellule;
import principal.Item;
import principal.Map;
import principal.Spawn;

public class MapTest {

	@Test
	public void test() {
		BufferedImage field = null;
		BufferedImage wall = null;
		Item e = new Item(2);
		Cellule start = new Cellule(e,true,5,5);
		Map carte = new Map(30,30,60,60,field,wall,start);
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
		Item e = new Item(2);
		Cellule start = new Cellule(e,true,5,5);
		Map carte = new Map(30,30,60,60,field,wall,start);
		Spawn s1 = new Spawn(8,3);
		Spawn s2 = new Spawn(2,5);
		Spawn s3 = new Spawn(4,7);
		Spawn s[] = {s1,s2,s3};	
		if(!carte.testMap(s,carte.chemin)) {
			fail("Mauvais Spawn");
		}
	}
	

}




