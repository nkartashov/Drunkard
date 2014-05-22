/**
 * SquareCellFactory
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

public class SquareCellFactory extends CellFactory {
	@Override
	public Cell[][] getCellArray(int height, int width) {
		return new SquareCell[height][width];
	}

	@Override
	public Cell getCell(CellOccupant occupant, int x, int y, Field field) {
		return new SquareCell(occupant, x, y, field);
	}
}
