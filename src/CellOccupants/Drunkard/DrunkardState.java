/**
 * DrunkardState

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
import Common.IDisplayable;

public interface DrunkardState extends IDisplayable {
	public Cell makeMove(Drunkard drunkard);
	public Cell acceptVisit(Drunkard drunkard);
	public String displayItself();
}
