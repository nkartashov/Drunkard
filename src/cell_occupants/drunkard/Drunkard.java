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

import common.cells.Cell;
import common.CellOccupant;

public class Drunkard extends CellOccupant {
	public Drunkard(Cell cell) {
		super(cell);
		setState(new NormalDrunkardState());
	}

	BottleObject getBottle() {return bottle;}
	void setBottle(BottleObject bottle) {
		this.bottle = bottle;
	}

	@Override
	public DrunkardState getState() {
		return (DrunkardState) super.getState();
	}

	private BottleObject bottle = new BottleObject();
}
