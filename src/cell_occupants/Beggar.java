/**
 * Beggar
 *
 * Version 1.0.0
 *
 * Created on 01/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants;


import common.cells.Cell;
import common.CellOccupant;

public class Beggar extends CellOccupant {
	public Beggar(Cell cell) {
		super(cell);
	}

	@Override
	public String displayItself() {
		return "z";
	}
}
