/**
 * CellTraits
 *
 * Version 1.0.0
 *
 * Created on 01/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common;

public class CellTraits {

	CellTraits() {}

	public void applyLight() {
		isLit = true;
	}

	public void applyDarkness() {
		isLit = false;
	}

	public boolean isLit() {
		return isLit;
	}

	private boolean isLit = false;
}
