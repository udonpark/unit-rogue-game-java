
package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import weapon.GiantAxe;

import java.util.ArrayList;

public class Undead extends Actor implements Resettable {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private ArrayList<Behaviour> behaviours = new ArrayList<>();
	private Actor player;

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 *
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new WanderBehaviour());
		this.player = Application.getPlayer();
		registerInstance();
	}

	@Override
	public void resetInstance() {
		;
	}

	@Override
	public boolean isExist() {
		return false;
	}


	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//         loop through all behaviours
		if (!this.isConscious()) {
			map.removeActor(this);
		}
        if(distance(map.locationOf(this), map.locationOf(player)) <= 1){
            return new AttackAction(player, "North");
        }
		if (distance(map.locationOf(this), map.locationOf(player)) <= 2) {
			behaviours.remove(0);
			behaviours.add(new FollowBehaviour(player));
		} else {
			behaviours.remove(0);
			behaviours.add(new WanderBehaviour());
		}
		for (Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}

