package common;

import cell_occupants.CellOccupant;

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

public class Cell implements IDisplayable {
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

	@Override
	public String displayItself() {
		return occupant.displayItself();
	}

	private final Field field;
	private CellOccupant occupant;
	private int x = -1;
	private int y = -1;
}
