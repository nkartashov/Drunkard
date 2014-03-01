/**
 * IDrunkard

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package CellOccupants.Drunkard;


import Common.Cell;

public interface IDrunkard {
	public Cell makeMove();
	public void changeState(DrunkardState state);
}
