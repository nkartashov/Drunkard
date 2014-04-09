/**
 * CellOccupant

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */

package common;

import common.actor_states.OccupantState;
import common.actor_states.PassiveState;


public abstract class CellOccupant implements IDisplayable, INotifiable {
	public CellOccupant(Cell cell) {
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public boolean isTraversible() {
		return state.isTraversible();
	}

	public String displayItself() {
		return state.displayItself();
	}

	public OccupantState getState() {
		return state;
	}

	public void setState(OccupantState state) {
		this.state = state;
	}

	protected void swapOccupants(CellOccupant occupant) {
		Cell oldCell = occupant.getCell();
		Cell newCell = getCell();
		oldCell.setOccupant(this);
		newCell.setOccupant(occupant);
	}

	@Override
	public void receiveNotification(int notification) {
		if (lastNotification != notification) {
			state.receiveNotification(notification, this);
			lastNotification = notification;
		}
	}

	private Cell cell;
	private OccupantState state = new PassiveState();
	private int lastNotification = -1;
}
