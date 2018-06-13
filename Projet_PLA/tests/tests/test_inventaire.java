package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import principal.*;

public class test_inventaire {

	@Test(expected = IllegalStateException.class)
	public void test1() {
		Inventaire inv;
		inv = new Inventaire();
		Item item1 = new Item(1,0,0,null,null,null);
		Item item2 = new Item(2,0,0,null,null,null);
		Item item3 = new Item(3,0,0,null,null,null);
		Item item4 = new Item(4,0,0,null,null,null);
		Item item5 = new Item(5,0,0,null,null,null);
		Item item6 = new Item(6,0,0,null,null,null);
		Item item7 = new Item(7,0,0,null,null,null);
		Item item8 = new Item(8,0,0,null,null,null);
		Item item9 = new Item(9,0,0,null,null,null);
		// Inventaire.Iterator iter;
		inv.enfiler(item1);
		inv.enfiler(item2);
		inv.enfiler(item3);
		inv.enfiler(item4);
		inv.enfiler(item5);
		inv.enfiler(item6);
		inv.enfiler(item7);
		inv.enfiler(item8);
		inv.enfiler(item9);
		// iter = inv.iterator();
	}

	@Test
	public void test2() {
		Inventaire inv;
		inv = new Inventaire();
		Item item1 = new Item(1,0,0,null,null,null);
		Item item2 = new Item(2,0,0,null,null,null);
		Item item3 = new Item(3,0,0,null,null,null);
		Item item4 = new Item(4,0,0,null,null,null);
		Item item5 = new Item(5,0,0,null,null,null);
		Item item6 = new Item(6,0,0,null,null,null);
		Item item7 = new Item(7,0,0,null,null,null);
		Item item8 = new Item(8,0,0,null,null,null);
		Item item9 = new Item(7,0,0,null,null,null);
		Item item10 = new Item(8,0,0,null,null,null);
		inv.enfiler(item1);
		inv.enfiler(item2);
		inv.enfiler(item3);
		inv.enfiler(item4);
		inv.enfiler(item5);
		inv.enfiler(item6);
		inv.enfiler(item7);
		inv.enfiler(item8);
		inv.enfiler(item9);
		inv.enfiler(item10);
		
		if (inv.head.number != 2) {

			fail("head.number devrait valoir 2 ");

		}
	}
	@Test
	public void test3() {
		Inventaire inv;
		inv = new Inventaire();
		Item item1 = new Item(1,0,0,null,null,null);
		Item item2 = new Item(1,0,0,null,null,null);
		Item item3 = new Item(3,0,0,null,null,null);
	
		inv.enfiler(item1);
		inv.enfiler(item2);
		inv.enfiler(item3);
		Item test3 = inv.defiler();
		Item test2 = inv.defiler();
		Item test1 = inv.defiler();
		Item test4 = inv.defiler();
		
		if(test1.type!= item1.type) {
			fail("probleme defilé");
		}
		if (test4!=null) {

			fail("file non vide");

		}
	}
}
