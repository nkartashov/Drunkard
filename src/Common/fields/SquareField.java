/**
 * SquareField
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
import common.cells.SquareCellFactory;

public class SquareField extends Field {
	public SquareField(int height, int width) throws IndexOutOfBoundsException {
		super(height, width, new SquareCellFactory());
	}

	@Override
	public void display(int move) {
		StringBuilder builder = new StringBuilder("Move #" + Integer.toString(move) + "\n");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cell cell = cells[i][j];
				builder.append(cell.displayItself());
			}
			builder.append("\n");
		}

		System.out.println(builder.toString());
	}
}
