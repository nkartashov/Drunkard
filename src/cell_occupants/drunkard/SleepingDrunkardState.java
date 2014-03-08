/**
 * SleepingDrunkardState

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants.drunkard;

import common.Cell;

public class SleepingDrunkardState implements DrunkardState {
	@Override
	public Cell makeMove(Drunkard drunkard) {
		return null;
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		drunkard.changeState(new SleepingDrunkardState());
		return null;
	}

	@Override
	public String displayItself() {
		return "Z";
	}
}
