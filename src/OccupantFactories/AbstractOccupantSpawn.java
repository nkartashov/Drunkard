/**
 * AbstractOccupantSpawn
 *
 * Version 1.0.0
 *
 * Created on 22/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package OccupantFactories;

import common.Field;

public abstract class AbstractOccupantSpawn {
	public AbstractOccupantSpawn(int x, int y, Field field) {
		this.x = x;
		this.y = y;
		this.field = field;
	}

	public abstract void spawnOccupant(int move);
	public void resetCooldown() {
		cooldown = DEFAULT_COOLDOWN;
	}
	public boolean isOnCooldown() {
		return cooldown != 0;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void tickCooldown() {
		cooldown = cooldown == 0 ? 0 : cooldown - 1;
	}

	public Field getField() {
		return field;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private final int x;
	private final int y;
	private final Field field;
	private int cooldown;
	private final int DEFAULT_COOLDOWN = 100;
}
