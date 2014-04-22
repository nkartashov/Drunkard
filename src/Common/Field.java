package common;

import OccupantFactories.AbstractOccupantSpawn;
import cell_occupants.Bottle;
import cell_occupants.Nobody;
import cell_occupants.drunkard.Drunkard;

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

	public void putObstacle(CellOccupant obstacle, int x, int y) throws IndexOutOfBoundsException {
		DoubleDispatch.dispatch(obstacle, getCell(x, y).getOccupant());
	}

	public void addSpawn(AbstractOccupantSpawn spawn) {
		spawns.add(spawn);
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

	public List<Cell> getAdjacentCells(int x, int y) {
		List<Cell> result = new ArrayList<>();
		int[] deltaX = new int[]{0, 0, -1, 1};
		int[] deltaY = new int[]{-1, 1, 0, 0};
		for (int i = 0; i < deltaX.length; i++) {
			int newX = x + deltaX[i];
			int newY = y + deltaY[i];
			if (isInField(newX, newY)) {
				result.add(getCell(newX, newY));
			}
		}
		return result;
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

	private Cell[][] cells;
	private final int height;
	private final int width;
	private final List<Bottle> bottles = new ArrayList<>();
	private final List<Drunkard> litDrunkards = new ArrayList<>();
	private final List<AbstractOccupantSpawn> spawns = new ArrayList<>();
}
