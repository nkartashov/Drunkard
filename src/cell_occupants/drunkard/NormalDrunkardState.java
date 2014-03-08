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

import common.Cell;

public class NormalDrunkardState implements DrunkardState {
	@Override
	public Cell makeMove(Drunkard drunkard) {
		int newX = drunkard.getCell().getX();
		int newY = drunkard.getCell().getY();

		double rnd = Math.random();
		if (rnd < 0.25) {
			newX -= 1;
		} else if (rnd < 0.5) {
			newY += 1;
		} else if (rnd < 0.75) {
			newY -= 1;
		} else {
			newX += 1;
		}

		if (!drunkard.getCell().getField().isInField(newX, newY)) {
			return null;
		}

		return drunkard.getCell().getField().getCell(newX, newY).getOccupant().acceptVisit(drunkard);
	}

	@Override
	public Cell acceptVisit(Drunkard drunkard) {
		return null;
	}

	@Override
	public String displayItself() {
		return "D";
	}
}
