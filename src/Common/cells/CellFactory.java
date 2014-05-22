/**
 * CellFactory
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

public abstract class CellFactory {
	public abstract Cell[][] getCellArray(int height, int width);
	public abstract Cell getCell(CellOccupant occupant, int x, int y, Field field);
}
