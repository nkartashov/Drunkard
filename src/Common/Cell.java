package common;

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

public class Cell implements IDisplayable, INotifiable {
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

	private final Field field;
	private CellOccupant occupant;
	private int x = -1;
	private int y = -1;
	private CellTraits cellTraits = new CellTraits();
}
