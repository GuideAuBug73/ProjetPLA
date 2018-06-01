package principal;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class MapTest {

	@Test
	public void test() {
		BufferedImage field = null;
		BufferedImage wall = null;
		Map carte = new Map(30,30,60,60,field,wall);
		
	}

}
