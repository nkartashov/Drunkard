/**
 * Tavern
 *
 * Version 1.0.0
 *
 * Created on 22/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package OccupantFactories;

import cell_occupants.drunkard.Drunkard;
import common.Cell;
import common.DoubleDispatch;
import common.Field;

public class Tavern extends AbstractOccupantSpawn {
	public Tavern(int x, int y, Field field) {
		super(x, y, field);
	}

	@Override
	public void spawnOccupant(int move) {
		final int DRUNKARD_COOLDOWN = 20;
		if (move % DRUNKARD_COOLDOWN != 0 || !getField().isInField(getX(), getY())) {
			return;
		}
		Cell cell = getField().getCell(getX(), getY());
		Drunkard drunkard = new Drunkard(cell);
		DoubleDispatch.dispatch(drunkard, cell.getOccupant());
	}
}
