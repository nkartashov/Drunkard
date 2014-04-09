/**
 * FallenDrunkardState

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants.drunkard;

import common.CellOccupant;

public class FallenDrunkardState extends DrunkardState {

	@Override
	public String displayItself() {
		return "&";
	}

	@Override
	public void receiveNotification(int notification, CellOccupant occupant) {
	}

	@Override
	public boolean isFallen() {
		return true;
	}

	@Override
	public boolean isSleeping() {
		return false;
	}
}
