/**
 * LampPost

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
import CellOccupants.Drunkard.SleepingDrunkardState;
import Common.Cell;

public class LampPost extends CellOccupant {
	public LampPost(Cell cell) {
		super(cell);
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		drunkard.changeState(new SleepingDrunkardState());
		return null;
	}

	@Override
	public Cell acceptVisit(CellOccupant occupant) {
		return null;
	}

	@Override
	public String displayItself() {
		return "C";
	}
}
