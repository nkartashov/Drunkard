package common;

import cell_occupants.CellOccupant;
import cell_occupants.drunkard.Drunkard;
import cell_occupants.Nobody;

import java.util.ArrayList;
import java.util.List;

/**
 * Field

 *
 * Version 1.0.0
 *
 * Created on 28/02/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */

public class Field implements INotifiable {
	public Field(int height, int width) throws IndexOutOfBoundsException {
		if (height <= 0 || width <= 0) {
			throw new IndexOutOfBoundsException();
		}

		this.height = height;
		this.width = width;
		cells = new Cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell(null, j, i, this);
				cells[i][j].setOccupant(new Nobody(cells[i][j]));
			}
		}
	}

	public Cell getCell(int x, int y) throws IndexOutOfBoundsException{
		return cells[y][x];
	}


	void putActor(CellOccupant actor, int x, int y) throws IndexOutOfBoundsException {
		getCell(x, y).setOccupant(actor);
	    addSubscriber((INotifiable) actor);
	}

	public void putObstacle(CellOccupant obstacle, int x, int y) throws IndexOutOfBoundsException {
		getCell(x, y).setOccupant(obstacle);
	}

	public void addSubscriber(INotifiable subscriber) {
		subscribers.add(subscriber);
	}

	public void removeSubscriber(INotifiable subscriber) {
		subscribers.remove(subscriber);
	}

	public void display(int move) {
		StringBuilder builder = new StringBuilder("Move #" + Integer.toString(move) + "\n");
		builder.append("         T    \n");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cell cell = cells[i][j];
				builder.append(cell.displayItself());
			}
			builder.append("\n");
		}

		System.out.println(builder.toString());
	}

	@Override
	public void receiveNotification(int notification) {
		final int DRUNKARD_COOLDOWN = 20;
		if (notification % DRUNKARD_COOLDOWN == 0)
			addDrunkard();
		for (INotifiable subscriber: subscribers) {
			subscriber.receiveNotification(notification);
		}
		display(notification);
	}

	public boolean isInField(int x, int y) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}

	private void addDrunkard() {
		int DRUNKARD_SPAWN_X = 9;
		int DRUNKARD_SPAWN_Y = 0;
		Cell cell = cells[DRUNKARD_SPAWN_Y][DRUNKARD_SPAWN_X];
		Drunkard drunkard = new Drunkard(cell);
		cell.getOccupant().acceptVisit(drunkard);
		addSubscriber(drunkard);
	}

	private Cell[][] cells;
	private final int height;
	private final int width;
	private final List<INotifiable> subscribers = new ArrayList<INotifiable>();

}
