package pathfinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import basic.Cellule;
import principal.Options;

/**
 * Creates nodes and neighbours from a 2d grid. Each point in the map has an
 * integer value that specifies the cost of crossing that point. If this value
 * is negative, the point is unreachable.
 * 
 * If diagonal movement is allowed, the Chebyshev distance is used, else
 * Manhattan distance is used.
 * 
 * @author Ben Ruijl
 * 
 */
public class Grid2d {
	//private final Cellule[][] map ;
	private Cellule[][] map = new Cellule[Options.nb_cell_h][Options.nb_cell_w];
	/**
	 * A node in a 2d map. This is simply the coordinates of the point.
	 * 
	 * @author Ben Ruijl
	 * 
	 */
	public class MapNode implements Node<MapNode> {
		private final int x, y;

		public MapNode(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double getHeuristic(MapNode goal) {

			return Math.abs(x - goal.x) + Math.abs(y - goal.y);

		}

		public double getTraversalCost(MapNode neighbour) {
			return 1 + map[neighbour.y][neighbour.x].cout;
		}

		public Set<MapNode> getNeighbours() {
			Set<MapNode> neighbours = new HashSet<MapNode>();

			for (int i = x ; i <= x + 1; i++) {
				for (int j = y; j <= y + 1; j++) {
					System.out.println("Options.nb_cell_w : "+Options.nb_cell_w);
					System.out.println("Options.nb_cell_h :"+Options.nb_cell_h);
					if ((i == x && j == y) || i < 0 || j < 0 || j >= Options.nb_cell_w || i >= Options.nb_cell_h) {
						continue;
					}

					if ((i < x && j < y) || (i > x && j > y)) {
						continue;
					}

					if (map[j][i].cout < 0) {
						continue;
					} 

					// TODO: create cache instead of recreation
					neighbours.add(new MapNode(i, j));
				}
			}

			return neighbours;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MapNode other = (MapNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private Grid2d getOuterType() {
			return Grid2d.this;
		}

	}

	public Grid2d(Cellule[][] map) {
		this.map = map;
	}

	public List<MapNode> findPath(int xStart, int yStart, int xGoal, int yGoal) {
		return PathFinding.doAStar(new MapNode(xStart, yStart), new MapNode(xGoal, yGoal));
	}

}
