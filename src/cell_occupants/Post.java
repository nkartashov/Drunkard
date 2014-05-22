/**
 * Post

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package cell_occupants;

import common.cells.Cell;
import common.CellOccupant;

public class Post extends CellOccupant {
	public Post(Cell cell) {
		super(cell);
	}

	@Override
	public String displayItself() {
		return "C";
	}
}
