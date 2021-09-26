package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}

	/**
	 * Allows player to enter valley tile
	 * Only player can step into Valley
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		if (actor instanceof Player) {
			return true;
		}
		else{ return false;}
	}

	/**
	 * returns a string representing the valley class
	 * @return string representing valley class
	 */
	@Override
	public String toString() {
		return "Valley";
	}
}
