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
package common;

import cell_occupants.LampPost;
import cell_occupants.Post;

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

		final int postX = 7;
		final int postY = 7;
		field.putObstacle(new Post(field.getCell(postX, postY)),
				postX, postY);

		final int lampPostX = 10;
		final int lampPostY = 3;
		field.putObstacle(new LampPost(field.getCell(lampPostX, lampPostY)), lampPostX, lampPostY);

		for (int deltaX = -3; deltaX < 4; deltaX++) {
			for (int deltaY = -3; deltaY < 4; deltaY++) {
				int x = lampPostX + deltaX;
				int y = lampPostY + deltaY;
				if (field.isInField(x, y)) {
					field.getCell(x, y).getCellTraits().applyLight();
				}
			}
		}
	}

	private static final int MAX_MOVES = 100;
	private Field field;
}
