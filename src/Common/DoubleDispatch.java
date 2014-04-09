/**
 * DoubleDispatch
 *
 * Version 1.0.0
 *
 * Created on 08/04/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common;

import cell_occupants.*;
import cell_occupants.drunkard.Drunkard;
import cell_occupants.drunkard.FallenDrunkardState;
import cell_occupants.drunkard.SleepingDrunkardState;
import common.actor_states.SuccessHandler;
import common.actor_states.TerminatingState;

public class DoubleDispatch {
	public static Cell dispatch(CellOccupant who, Nobody where) {
		Cell oldCell = who.getCell();
		where.swapOccupants(who);
		return oldCell;
	}

	public static Cell dispatch(final Beggar who, Bottle where) {
		final Field field = who.getCell().getField();
		field.removeOccupant(where.getCell());
		field.notifyBottleDealtWith(where);
		Cell goal = field.getCell(field.BEGGAR_SPAWN_X, field.BEGGAR_SPAWN_Y);
		SuccessHandler handler = new SuccessHandler() {
			@Override
			public void handle() {
				field.removeOccupant(who.getCell());
				field.resetBeggarCooldown();
			}
		};
		who.setState(new TerminatingState(new Bfs(goal, field), handler));
		return null;
	}

	public static Cell dispatch(Drunkard who, Bottle where) {
		who.setState(new FallenDrunkardState());
		who.getCell().getField().notifyFallenSleepingDrunkard(who);
		return null;
	}

	public static Cell dispatch(Drunkard who, Post where) {
		who.setState(new SleepingDrunkardState());
		who.getCell().getField().notifyFallenSleepingDrunkard(who);
		return null;
	}

	public static Cell dispatch(Drunkard who, Drunkard where) {
		if (where.getState().isSleeping()) {
			who.setState(new SleepingDrunkardState());
			who.getCell().getField().notifyFallenSleepingDrunkard(who);
		}
		return null;
	}

	public static Cell dispatch(final Policeman who, Drunkard where) {
		if (where.getState().isSleeping() || where.getState().isFallen()) {
			final Field field = who.getCell().getField();
			field.removeOccupant(where.getCell());
			field.notifyDrunkardDealtWith(where);
			Cell goal = field.getCell(field.POLICEMAN_SPAWN_X, field.POLICEMAN_SPAWN_Y);
			SuccessHandler handler = new SuccessHandler() {
				@Override
				public void handle() {
					field.removeOccupant(who.getCell());
					field.resetPolicemanCooldown();
				}
			};
			who.setState(new TerminatingState(new Bfs(goal, field), handler));
		}
		return null;
	}

	public static Cell dispatch(CellOccupant who, CellOccupant where) {
		if (where instanceof Nobody) {
			return dispatch(who, (Nobody) where);
		} else if (who instanceof Drunkard) {
			Drunkard drunkard = (Drunkard) who;
			if (where instanceof Bottle) {
				return dispatch(drunkard, (Bottle) where);
			} else if (where instanceof Post) {
				return dispatch(drunkard, (Post) where);
			} else if (where instanceof Drunkard) {
				return dispatch(drunkard, (Drunkard) where);
			} else {
				return defaultDispatch();
			}
		} else if (who instanceof Policeman) {
			Policeman policeman = (Policeman) who;
			if (where instanceof Drunkard) {
				return dispatch(policeman, (Drunkard) where);
			} else {
				return defaultDispatch();
			}
		} else if (who instanceof Beggar) {
			Beggar beggar = (Beggar) who;
			if (where instanceof Bottle) {
				return dispatch(beggar, (Bottle) where);
			} else {
				return defaultDispatch();
			}
		} else {
			return null;
		}
	}

	public static Cell defaultDispatch() {
		return null;
	}
}
