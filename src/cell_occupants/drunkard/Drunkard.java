/**
 * Drunkard

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
import cell_occupants.CellOccupant;
import common.Cell;
import common.INotifiable;

public class Drunkard extends CellOccupant implements INotifiable, IDrunkard {
	public Drunkard(Cell cell) {
		super(cell);
	}

	@Override
	public void receiveNotification(int notification) {
		Cell moveResult = makeMove();
		handleMoveResult(moveResult);
	}

	@Override
	public Cell makeMove() {
		return state.makeMove(this);
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		return state.acceptVisit(drunkard);
	}

	@Override
	public Cell acceptVisit(CellOccupant occupant) {
		return null;
	}

	@Override
	public String displayItself() {
		return state.displayItself();
	}

	public void changeState(DrunkardState state) {
		this.state = state;
	}

	private void handleMoveResult(Cell cell) {
		if (cell != null) {
			if (bottle != null && (int) (Math.random() * 30) == 1) {
				cell.setOccupant(new Bottle(cell));
				bottle = null;
			}
		}
	}

	private BottleObject bottle = new BottleObject();
	private DrunkardState state = new NormalDrunkardState();
}
