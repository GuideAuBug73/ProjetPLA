
public class Inventaire {
	class Cell {
		Item elem;
		Cell next;

		Cell(Cell prev, Item elem) {
			prev.next.elem = elem;
			prev.next = next;
		}

		Cell(Item elem) {
			this.elem = elem;
		}
	}

	Cell head; // head of the list of cells
	Cell last;
	int ncells; // number of cells in the list

}
