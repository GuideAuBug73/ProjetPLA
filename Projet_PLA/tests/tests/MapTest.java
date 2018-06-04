package tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

import principal.Map;

public class MapTest {

	@Test
	public void test() {
		BufferedImage field = null;
		BufferedImage wall = null;
		Map carte = new Map(30,30,60,60,field,wall,0);
		for (int i = 0; i < 30; i++)
		{
			for(int j = 0; j < 30; j++) {
				System.out.println(carte.cellules[i][j].libre);
				System.out.println(carte.cellules[i][j].x);
				System.out.println(carte.cellules[i][j].y);
			}
		}
	}

}




