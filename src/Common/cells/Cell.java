package common.cells;

import common.*;
import common.fields.Field;
import common.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Cell

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */

public abstract class Cell implements IDisplayable, INotifiable {
	public Cell(CellOccupant occupant, int x, int y, Field field) throws IndexOutOfBoundsException {
		if (x < 0 || y < 0) {
			throw new IndexOutOfBoundsException();
		}

		this.occupant = occupant;
		this.x = x;
		this.y = y;
		this.field = field;
	}

	public CellOccupant getOccupant() {
		return occupant;
	}

	public void setOccupant(CellOccupant occupant) {
		this.occupant = occupant;
		occupant.setCell(this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Field getField() {
		return field;
	}

	public CellTraits getCellTraits() {
		return cellTraits;
	}

	@Override
	public String displayItself() {
		return occupant.displayItself();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Cell) {
			Cell cell = (Cell) object;
			return cell.getX() == x && cell.getY() == y;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public void receiveNotification(int notification) {
		occupant.receiveNotification(notification);
	}

	public boolean isTraversible() {
		return occupant.isTraversible();
	}

	public List<Cell> neighbours() {
		if (neigbouringCells != null) {
			return neigbouringCells;
		}

		List<Cell> result = new ArrayList<>();
		for (Tuple<Integer, Integer> delta: deltas) {
			int newX = x + delta.fst;
			int newY = y + delta.snd;
			if (field.isInField(x + delta.fst, y + delta.snd)) {
				result.add(field.getCell(newX, newY));
			}
		}
		neigbouringCells = result;
		return result;
	}

	protected void addDelta(Tuple<Integer, Integer> delta) {
		deltas.add(delta);
	}

	protected abstract void addDeltas();

	private final Field field;
	private CellOccupant occupant;
	private int x = -1;
	private int y = -1;
	private final CellTraits cellTraits = new CellTraits();
	private List<Tuple<Integer, Integer>> deltas = new ArrayList<>();
	private List<Cell> neigbouringCells = null;
}
