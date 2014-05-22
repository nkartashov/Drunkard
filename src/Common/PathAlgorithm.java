/**
 * PathAlgorithm
 *
 * Version 1.0.0
 *
 * Created on 01/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common;

import common.cells.Cell;

public interface PathAlgorithm {
	public Cell getNextCell(Cell start);
	public Cell getGoal();
}
