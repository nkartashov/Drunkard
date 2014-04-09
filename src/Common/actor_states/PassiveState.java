/**
 * PassiveState
 *
 * Version 1.0.0
 *
 * Created on 08/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.actor_states;

import common.CellOccupant;

public class PassiveState extends OccupantState {

	@Override
	public String displayItself() {
		return ".";
	}

	@Override
	public void receiveNotification(int notification, CellOccupant occupant) {
	}
}
