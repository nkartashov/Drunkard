/**
 * HexagonalCell
 *
 * Version 1.0.0
 *
 * Created on 21/05/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.cells;

import common.CellOccupant;
import common.fields.Field;
import common.utils.Tuple;

public class HexagonalCell extends Cell {
	public HexagonalCell(CellOccupant occupant, int x, int y, Field field) throws IndexOutOfBoundsException {
		super(occupant, x, y, field);
		addDeltas();
	}

	@Override
	protected void addDeltas()  {
		int[] deltasX = {-1, 1, 0, 0};
		int[] deltasY = {0, 0, -1, 1};
		for (int i = 0; i < deltasX.length; i++) {
			addDelta(new Tuple<>(deltasX[i], deltasY[i]));
		}
		if (getY() % 2 == 0) {
			deltasX = new int[]{1, 1};
			deltasY = new int[]{-1, 1};
		} else {
			deltasX = new int[]{-1, -1};
			deltasY = new int[]{-1, 1};
		}
		for (int i = 0; i < deltasX.length; i++) {
			addDelta(new Tuple<>(deltasX[i], deltasY[i]));
		}
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof HexagonalCell) {
			Cell cell = (Cell) object;
			return cell.getX() == getY() && cell.getY() == getY();
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + getX();
		result = prime * result + getY();
		return result;
	}
}
