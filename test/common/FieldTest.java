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

import common.fields.Field;
import common.fields.SquareField;
import org.junit.Assert;
import org.junit.Test;

public class FieldTest {
	@Test
	public void adjacentCellsTest() {
		Field field = new SquareField(100, 100);
		Assert.assertEquals(2, field.getCell(0, 0).neighbours().size());
		Assert.assertEquals(4, field.getCell(33, 33).neighbours().size());
	}
}
