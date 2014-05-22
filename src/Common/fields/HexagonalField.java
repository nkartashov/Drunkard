/**
 * HexagonalField
 *
 * Version 1.0.0
 *
 * Created on 23/05/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.fields;

import common.cells.Cell;
import common.cells.HexagonalCellFactory;

public class HexagonalField extends Field {
	public HexagonalField(int height, int width) throws IndexOutOfBoundsException {
		super(height, width, new HexagonalCellFactory());
	}

	@Override
	public void display(int move) {
		StringBuilder builder = new StringBuilder("Move #" + Integer.toString(move) + "\n");
		for (int i = 0; i < height; i++) {
			if (i % 2 == 0) {
				builder.append(" ");
			}
			for (int j = 0; j < width; j++) {
				Cell cell = cells[i][j];
				builder.append(cell.displayItself()).append(" ");
			}
			builder.append("\n");
		}

		System.out.println(builder.toString());
	}
}
