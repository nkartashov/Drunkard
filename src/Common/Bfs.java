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

import common.cells.Cell;

import java.util.*;

public class Bfs implements PathAlgorithm {
	public Bfs(Cell goal) {
		this.goal = goal;
	}

	@Override
	public Cell getGoal() {
		return goal;
	}

	@Override
	public Cell getNextCell(Cell start) {
		if (start == goal) {
			return null;
		}

		distances.put(start, 0);
		toVisit.add(start);
		while (!toVisit.isEmpty()) {
			Cell newCell = toVisit.poll();
			visited.add(newCell);
			for (Cell c : newCell.neighbours()) {
				if (!visited.contains(c)) {
					if (c.isTraversible() || c == goal) {
						parents.put(c, newCell);
						distances.put(c, distances.get(newCell) + 1);
						if (c != goal) {
							toVisit.add(c);
						} else {
							toVisit.clear();
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
		distances.clear();
		visited.clear();
		parents.clear();
		return current;
	}

	private Cell goal;
	private final Set<Cell> visited = new HashSet<>();
	private final Map<Cell, Integer> distances = new HashMap<>();
	private final Map<Cell, Cell> parents = new HashMap<>();
	private final Queue<Cell> toVisit = new ArrayDeque<>();
}
