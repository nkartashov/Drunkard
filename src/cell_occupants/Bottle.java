/**
 * Bottle

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants;

import common.Cell;
import common.CellOccupant;

public class Bottle extends CellOccupant {
	public Bottle(Cell cell) {
		super(cell);
		cell.getField().notifyBottle(this);
	}

	@Override
	public String displayItself() {
		return "B";
	}
}
