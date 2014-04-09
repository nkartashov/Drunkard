package common;

import cell_occupants.Beggar;
import cell_occupants.Bottle;
import cell_occupants.Nobody;
import cell_occupants.Policeman;
import cell_occupants.drunkard.Drunkard;
import common.actor_states.MovingState;
import common.actor_states.OccupantState;
import common.actor_states.PassiveState;

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
		getCell(x, y).setOccupant(obstacle);
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
		final int DRUNKARD_COOLDOWN = 20;
		if (notification % DRUNKARD_COOLDOWN == 0) {
			addDrunkard();
		}
		if (beggarCooldown == 0) {
			addBeggar();
		} else {
			if (beggarCooldown > 0) {
				beggarCooldown--;
			}
		}
		if (policemanCooldown == 0 && !litDrunkards.isEmpty()) {
			addPoliceman();
		} else {
			if (policemanCooldown > 0) {
				policemanCooldown--;
			}
		}
		informSubscribersAboutMove(notification);
		display(notification);
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
		if (beggar.getState() instanceof PassiveState) {
			Cell goal = bottle.getCell();
			beggar.setState(new MovingState(new Bfs(goal, this)));
		}
	}

	public void notifyBottleDealtWith(Bottle bottle) {
		if (bottles.contains(bottle)) {
			bottles.remove(bottle);
		}
	}

	public void removeOccupant(Cell cell) {
		cell.setOccupant(new Nobody(cell));
	}

	public void resetBeggarCooldown() {
		beggarCooldown = 30;
	}

	public void resetPolicemanCooldown() {
		policemanCooldown = 0;
	}

	public final int POLICEMAN_SPAWN_X = 14;
	public final int POLICEMAN_SPAWN_Y = 3;
	public final int DRUNKARD_SPAWN_X = 9;
	public final int DRUNKARD_SPAWN_Y = 0;
	public final int BEGGAR_SPAWN_X = 0;
	public final int BEGGAR_SPAWN_Y = 4;

	private void informSubscribersAboutMove(int notification) {
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; j++) {
				cells[i][j].receiveNotification(notification);
			}
		}
	}

	private void addDrunkard() {
		if (!isInField(DRUNKARD_SPAWN_X, DRUNKARD_SPAWN_Y)) {
			return;
		}
		Cell cell = cells[DRUNKARD_SPAWN_Y][DRUNKARD_SPAWN_X];
		Drunkard drunkard = new Drunkard(cell);
		DoubleDispatch.dispatch(drunkard, cell.getOccupant());
	}

	private void addPoliceman() {
		Cell cell = cells[POLICEMAN_SPAWN_Y][POLICEMAN_SPAWN_X];
		Policeman policeman = new Policeman(cell);
		OccupantState movingState = new MovingState(new Bfs(litDrunkards.get(0).getCell(), this));
		policeman.setState(movingState);
		DoubleDispatch.dispatch(policeman, cell.getOccupant());
		policemanCooldown = -1;
	}

	private void addBeggar() {
		Cell cell = cells[BEGGAR_SPAWN_Y][BEGGAR_SPAWN_X];
		beggar = new Beggar(cell);
		OccupantState movingState;
		if (!bottles.isEmpty()) {
			movingState = new MovingState(new Bfs(bottles.get(0).getCell(), this));
		} else {
			movingState = new PassiveState();
		}
		beggar.setState(movingState);
		DoubleDispatch.dispatch(beggar, cell.getOccupant());
		beggarCooldown = -1;
	}

	private Cell[][] cells;
	private final int height;
	private final int width;
	private final List<Bottle> bottles = new ArrayList<>();
	private final List<Drunkard> litDrunkards = new ArrayList<>();
	private int beggarCooldown = 0;
	private int policemanCooldown = 0;
	private Beggar beggar;
}
