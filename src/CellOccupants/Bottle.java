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
package CellOccupants;

import CellOccupants.Drunkard.Drunkard;
import CellOccupants.Drunkard.FallenDrunkardState;
import Common.Cell;

public class Bottle extends CellOccupant {
	public Bottle(Cell cell) {
		super(cell);
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		drunkard.changeState(new FallenDrunkardState());
		return null;
	}

	@Override
	public Cell acceptVisit(CellOccupant occupant) {
		return null;
	}

	@Override
	public String displayItself() {
		return "B";
	}
}
