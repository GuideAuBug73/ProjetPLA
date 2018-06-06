package principal;

public class Inventaire {
	public class Cell {
		Item elem;
		public int number;
		Cell next;

		Cell(Cell prev, Item elem) {
			prev.next.elem = elem;
			prev.next = next;
			this.number = 1 ;
		}

		Cell(Item elem) {
			this.elem = elem;
			this.number = 1 ;
		}
	}

	public Cell head; // head of the list of cells
	int ncells; // number of cells in the list

	public class Iterator {
		int i;
		Cell courante;

		public Iterator() {
			this.i = 0;
			this.courante = Inventaire.this.head;

		}

		public boolean hasNext() {
			return i < (Inventaire.this.ncells);
		}

		public Object next() {
			this.i++;
			Object tmp = courante.elem;
			courante = courante.next;
			return tmp;
		}
	}

	public Iterator iterator() {
		return new Iterator();
	}

	public Inventaire() {
		this.ncells = 0;
		this.head = null;
	}

	public void enfiler(Item elem) {

		Cell trouve = research(elem);
		if (trouve != null) {
			trouve.number++;
		} else if (ncells >= 8) {
			throw new IllegalStateException("nombre item max dans inventaire déjà atteint");
		} else if (head == null) {
			head = new Cell(elem);
			ncells = 1;
		} else {
			Cell tempo = new Cell(elem);
			tempo.next = head;
			head = tempo;
			ncells++;
		}
	}

	public Item defiler() {
		if (head == null) {
			return null;
		}
		if (head.number > 1) {
			head.number--;
			return head.elem;
		} else {
			Cell tempo = head;
			head = head.next;
			ncells--;
			return tempo.elem;
		}
	}

	public Cell research(Item elem) {
		Iterator iter = this.iterator();
		Cell current = head;
		while (iter.hasNext()) {
			if (current.elem.type == elem.type) {
				return current;
			}
			iter.next();
			//current = current.next;
		}
		return null;
	}

}
