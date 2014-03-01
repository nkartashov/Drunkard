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

package CellOccupants;

import CellOccupants.Drunkard.Drunkard;
import Common.Cell;
import Common.IDisplayable;


public abstract class CellOccupant implements IDisplayable {
	public CellOccupant(Cell cell) {
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	protected void swapOccupants(CellOccupant occupant) {
		Cell oldCell = occupant.getCell();
		Cell newCell = getCell();
		oldCell.setOccupant(this);
		newCell.setOccupant(occupant);
	}

	public abstract Cell acceptVisit(Drunkard drunkard);
	public abstract Cell acceptVisit(CellOccupant occupant);

	private Cell cell;
}
