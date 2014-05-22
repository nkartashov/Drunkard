/**
 * BeggarSpawn
 *
 * Version 1.0.0
 *
 * Created on 22/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package OccupantFactories;

import cell_occupants.Beggar;
import cell_occupants.Bottle;
import common.Bfs;
import common.cells.Cell;
import common.DoubleDispatch;
import common.fields.Field;
import common.actor_states.MovingState;
import common.actor_states.OccupantState;
import common.actor_states.PassiveState;

public class BeggarSpawn extends AbstractOccupantSpawn {
	public BeggarSpawn(int x, int y, Field field) {
		super(x, y, field);
	}

	@Override
	public void spawnOccupant(int move) {
		if (isOnCooldown() || !getField().isInField(getX(), getY())) {
			if (beggar != null) {
				checkBottles();
			}
			return;
		}

		Cell cell = getField().getCell(getX(), getY());
		beggar = new Beggar(cell);
		OccupantState movingState;
		if (!getField().getBottles().isEmpty()) {
			movingState = new MovingState(
					new Bfs(getField().getBottles().get(0).getCell()
					));
		} else {
			movingState = new PassiveState();
		}
		beggar.setState(movingState);
		beggar.setSpawn(this);
		if (DoubleDispatch.dispatch(beggar, cell.getOccupant()) != null) {
			setCooldown(-1);
		}
	}

	@Override
	public void resetCooldown() {
		final int BEGGAR_COOLDOWN = 30;
		setCooldown(BEGGAR_COOLDOWN);
		beggar = null;
	}

	private void checkBottles() {
		if (!getField().getBottles().isEmpty() &&
				beggar != null &&
				beggar.getState() instanceof PassiveState) {
			Bottle bottle = getField().getBottles().get(0);
			Cell goal = bottle.getCell();
			beggar.setState(new MovingState(new Bfs(goal)));
		}
	}

	private Beggar beggar = null;
}
