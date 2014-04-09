/**
 * TerminatingState
 *
 * Version 1.0.0
 *
 * Created on 09/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.actor_states;

import common.Cell;
import common.CellOccupant;
import common.DoubleDispatch;
import common.PathAlgorithm;

public class TerminatingState extends MovingState {
	public TerminatingState(PathAlgorithm algorithm, SuccessHandler handler) {
		super(algorithm);
		this.handler = handler;
	}

	@Override
	public void receiveNotification(int notification, CellOccupant occupant) {
		Cell newCell = algorithm.getNextCell(occupant.getCell());
		if (newCell != null) {
			DoubleDispatch.dispatch(occupant, newCell.getOccupant());
			if (occupant.getCell() == algorithm.getGoal()) {
				handler.handle();
			}
		}
	}

	private SuccessHandler handler;
}
