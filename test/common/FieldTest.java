/**
 * FieldTest
 *
 * Version 1.0.0
 *
 * Created on 09/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common;

import org.junit.Assert;
import org.junit.Test;

public class FieldTest {
	@Test
	public void adjacentCellsTest() {
		Field field = new Field(100, 100);
		Assert.assertEquals(2, field.getAdjacentCells(0, 0).size());
		Assert.assertEquals(4, field.getAdjacentCells(33, 33).size());
	}
}
