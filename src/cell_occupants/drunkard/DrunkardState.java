/**
 * DrunkardState
 *
 * Version 1.0.0
 *
 * Created on 09/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants.drunkard;

import common.actor_states.OccupantState;

public abstract class DrunkardState extends OccupantState {
	public abstract boolean isFallen();
	public abstract boolean isSleeping();
}
