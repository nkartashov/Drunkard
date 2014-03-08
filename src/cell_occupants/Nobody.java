/**
 * Nobody

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants;

import cell_occupants.drunkard.Drunkard;
import common.Cell;

public class Nobody extends CellOccupant {
	public Nobody(Cell cell) {
		super(cell);
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		return acceptVisit((CellOccupant) drunkard);
	}

	@Override
	public Cell acceptVisit(CellOccupant occupant) {
		Cell oldCell = occupant.getCell();
		swapOccupants(occupant);
		return oldCell;
	}

	@Override
	public String displayItself() {
		return ".";
	}
}
