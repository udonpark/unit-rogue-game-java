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
	 * Allows player ot enter valley tile
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return true;
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
