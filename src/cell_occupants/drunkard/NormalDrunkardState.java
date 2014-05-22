/**
 * NormalState

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants.drunkard;

import cell_occupants.Bottle;
import common.cells.Cell;
import common.CellOccupant;
import common.DoubleDispatch;

import java.util.List;
import java.util.Random;

public class NormalDrunkardState extends DrunkardState {

	@Override
	public String displayItself() {
		return "D";
	}

	@Override
	public void receiveNotification (int notification, CellOccupant occupant) {
		Random random = new Random();
		List<Cell> neighbours = occupant.getCell().neighbours();
		int index = random.nextInt(neighbours.size());
		Cell newCell = neighbours.get(index);
	 	Cell oldCell = DoubleDispatch.dispatch(occupant, newCell.getOccupant());
		if (oldCell != null) {
			handleMoveResult(oldCell, (Drunkard) occupant);
		}
	}

	private void handleMoveResult(Cell cell, Drunkard drunkard) {
		if (cell != null) {
			if (drunkard.getBottle() != null && (int) (Math.random() * 30) == 1) {
				cell.setOccupant(new Bottle(cell));
				drunkard.setBottle(null);
			}
		}
	}

	@Override
	public boolean isFallen() {
		return false;
	}

	@Override
	public boolean isSleeping() {
		return false;
	}
}
