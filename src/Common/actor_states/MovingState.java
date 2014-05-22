/**
 * MovingState
 *
 * Version 1.0.0
 *
 * Created on 09/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.actor_states;

import common.cells.Cell;
import common.CellOccupant;
import common.DoubleDispatch;
import common.PathAlgorithm;


public class MovingState extends OccupantState {
	public MovingState(PathAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public String displayItself() {
		return null;
	}

	@Override
	public void receiveNotification(int notification, CellOccupant occupant) {
		Cell newCell = algorithm.getNextCell(occupant.getCell());
		if (newCell != null) {
			DoubleDispatch.dispatch(occupant, newCell.getOccupant());
		}
	}

	protected PathAlgorithm algorithm;
}
