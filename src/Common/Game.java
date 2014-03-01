/**
 * Game

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package Common;

import CellOccupants.LampPost;

public class Game {
	public Game() {
		init();
	}

	public void run() {
		for (int i = 0; i < MAX_MOVES; i++) {
			field.receiveNotification(i);
		}
	}

	private void init() {
		final int fieldHeight = 15;
		final int fieldWidth = 15;
		field = new Field(fieldHeight, fieldWidth);

		final int lampPostX = 7;
		final int lampPostY = 7;
		field.putObstacle(new LampPost(field.getCell(lampPostX, lampPostY)),
				lampPostX, lampPostY);
	}

	private static final int MAX_MOVES = 100;
	private Field field;
}
