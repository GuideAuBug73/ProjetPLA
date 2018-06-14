package pathfinding;

import basic.Cellule;
import principal.Options;

public class Example {
	public Example() {
		Cellule[][] map = new Cellule[Options.nb_cell_h][Options.nb_cell_w] ;
		Grid2d map2d = new Grid2d(map);
		System.out.println(map2d.findPath(100/Options.TAILLE_CELLULE, 0, 0, 0));
	}

	public static void main(String[] args) {
		new Example();
	}

}
