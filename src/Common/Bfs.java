/**
 * Bfs
 *
 * Version 1.0.0
 *
 * Created on 01/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common;

import java.util.*;

public class Bfs implements PathAlgorithm {
	public Bfs(Cell goal, Field field) {
		this.goal = goal;
		this.field = field;
	}

	@Override
	public Cell getGoal() {
		return goal;
	}

	@Override
	public Cell getNextCell(Cell start) {
		if (start == goal)
			return null;

		distances.put(start, 0);
		toVisit.add(start);

		while (!toVisit.isEmpty()) {
			Cell newCell = toVisit.poll();
			visited.add(newCell);
			for (Cell c : field.getAdjacentCells(newCell.getX(), newCell.getY())) {
				if (!visited.contains(c)) {
					if (c.isTraversible() || c == goal) {
						parents.put(c, newCell);
						distances.put(c, distances.get(newCell) + 1);
						if (c != goal) {
							toVisit.add(c);
						} else {
							break;
						}
					}
				}
			}
		}
		if (distances.containsKey(goal)) {
			return backtrace(start);
		} else {
			return null;
		}
	}

	private Cell backtrace(Cell start) {
		Cell current = goal;
		while (parents.get(current) != start) {
			current = parents.get(current);
		}
		return current;
	}

	private Cell goal;
	private Field field;
	private Set<Cell> visited = new HashSet<>();
	private Map<Cell, Integer> distances = new HashMap<>();
	private Map<Cell, Cell> parents = new HashMap<>();
	private Queue<Cell> toVisit = new ArrayDeque<>();
}
