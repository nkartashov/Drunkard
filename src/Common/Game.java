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

import OccupantFactories.BeggarSpawn;
import OccupantFactories.Tavern;
import cell_occupants.LampPost;
import cell_occupants.Post;
import common.fields.Field;
import common.fields.HexagonalField;

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
		field = new HexagonalField(fieldHeight, fieldWidth);

		final int postX = 7;
		final int postY = 7;
		field.putObstacle(new Post(field.getCell(postX, postY)),
				postX, postY);

		final int lampPostX = 10;
		final int lampPostY = 3;
		field.putObstacle(new LampPost(field.getCell(lampPostX, lampPostY)), lampPostX, lampPostY);

		final int tavernX = 9;
		final int tavernY = 0;
		field.addSpawn(new Tavern(tavernX, tavernY, field));

		final int policeStationX = 14;
		final int policeStationY = 3;
		//field.addSpawn(new PoliceStation(policeStationX, policeStationY, field));

		final int beggarSpawnX = 0;
		final int beggarSpawnY = 4;
		field.addSpawn(new BeggarSpawn(beggarSpawnX, beggarSpawnY, field));
	}

	private static final int MAX_MOVES = 100;
	private Field field;
}
