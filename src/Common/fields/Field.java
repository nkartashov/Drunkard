package common.fields;

import OccupantFactories.AbstractOccupantSpawn;
import cell_occupants.Bottle;
import cell_occupants.Nobody;
import cell_occupants.drunkard.Drunkard;
import common.CellOccupant;
import common.DoubleDispatch;
import common.INotifiable;
import common.cells.Cell;
import common.cells.CellFactory;

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

public abstract class Field implements INotifiable {
	public Field(int height, int width, CellFactory cellFactory) throws IndexOutOfBoundsException {
		if (height <= 0 || width <= 0) {
			throw new IndexOutOfBoundsException();
		}

		this.height = height;
		this.width = width;
		cells = cellFactory.getCellArray(height, width);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = cellFactory.getCell(null, j, i, this);
				cells[i][j].setOccupant(new Nobody(cells[i][j]));
			}
		}
	}

	public Cell getCell(int x, int y) throws IndexOutOfBoundsException{
		return cells[y][x];
	}

	public void putObstacle(CellOccupant obstacle, int x, int y) throws IndexOutOfBoundsException {
		DoubleDispatch.dispatch(obstacle, getCell(x, y).getOccupant());
	}

	public void addSpawn(AbstractOccupantSpawn spawn) {
		spawns.add(spawn);
	}

	public abstract void display(int move);

	public void receiveNotification(int notification) {
		informSpawnsAboutMove(notification);
		informSubscribersAboutMove(notification);
		display(notification);
	}

	public void informSpawnsAboutMove(int move) {
		for (AbstractOccupantSpawn spawn : spawns) {
			spawn.spawnOccupant(move);
		}
	}

	public boolean isInField(int x, int y) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}

	public void notifyFallenSleepingDrunkard(Drunkard drunkard) {
		if (drunkard.getCell().getCellTraits().isLit())
			litDrunkards.add(drunkard);
	}

	public void notifyDrunkardDealtWith(Drunkard drunkard) {
		if (litDrunkards.contains(drunkard)) {
			litDrunkards.remove(drunkard);
		}
	}

	public void notifyBottle(Bottle bottle) {
		bottles.add(bottle);
	}

	public void notifyBottleDealtWith(Bottle bottle) {
		if (bottles.contains(bottle)) {
			bottles.remove(bottle);
		}
	}

	public void removeOccupant(Cell cell) {
		cell.setOccupant(new Nobody(cell));
	}

	public List<Drunkard> getLitDrunkards() {
		return litDrunkards;
	}

	public List<Bottle> getBottles() {
		return bottles;
	}

	private void informSubscribersAboutMove(int notification) {
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; j++) {
				cells[i][j].receiveNotification(notification);
			}
		}
	}

	protected final Cell[][] cells;
	protected final int height;
	protected final int width;
	private final List<Bottle> bottles = new ArrayList<>();
	private final List<Drunkard> litDrunkards = new ArrayList<>();
	private final List<AbstractOccupantSpawn> spawns = new ArrayList<>();
}
