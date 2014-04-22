/**
 * PoliceStation
 *
 * Version 1.0.0
 *
 * Created on 22/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package OccupantFactories;

import cell_occupants.Policeman;
import common.Bfs;
import common.Cell;
import common.DoubleDispatch;
import common.Field;
import common.actor_states.MovingState;
import common.actor_states.OccupantState;

public class PoliceStation extends AbstractOccupantSpawn {
	public PoliceStation(int x, int y, Field field) {
		super(x, y, field);
	}

	@Override
	public void spawnOccupant(int move) {
		if (isOnCooldown() ||
				getField().getLitDrunkards().isEmpty() ||
				!getField().isInField(getX(), getY())) {
			return;
		}
		Cell cell = getField().getCell(getX(), getY());
		Policeman policeman = new Policeman(cell);
		OccupantState movingState = new MovingState(
				new Bfs(getField().getLitDrunkards().get(0).getCell(),
						getField()));
		policeman.setState(movingState);
		policeman.setSpawn(this);
		if (DoubleDispatch.dispatch(policeman, cell.getOccupant()) != null) {
			setCooldown(-1);
		}
	}

	public void resetCooldown() {
		setCooldown(0);
	}
}
