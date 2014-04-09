/**
 * Nobody

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants;

import common.Cell;
import common.CellOccupant;

public class Nobody extends CellOccupant {
	public Nobody(Cell cell) {
		super(cell);
	}

	@Override
	public String displayItself() {
		return ".";
	}

	@Override
	public boolean isTraversible() {
		return true;
	}
}
