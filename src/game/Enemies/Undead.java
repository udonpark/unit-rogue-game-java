
package game.Enemies;


import edu.monash.fit2099.engine.*;
import game.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import weapon.GiantAxe;

import java.util.ArrayList;
import java.util.Random;

public class Undead extends Enemies implements Resettable {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private ArrayList<Behaviour> behaviours = new ArrayList<>();
	private Player player;
	private boolean inRange = false;

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new WanderBehaviour());
		this.player = Application.getPlayer();
		registerInstance();
	}

	/**
	 *
	 */
	@Override
	public void resetInstance() {
	}

	/**
	 * Overiddes isExist
	 * @return the existence of the instance in the game.
	 */
	@Override
	public boolean isExist() {
		return false;
	}

	/**
	 *
	 * @return new Intrinsic Weapon with 20 damage
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}

	/**
	 * Called every turn and executes an action, if player is in adjacent blocks it attacks. if player is 2 blacks away
	 * it follows the player, else it just wanders around
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//         loop through all behaviours
     	Random rand = new Random();
		if (!this.isConscious()) {
			map.removeActor(this);
			display.println("Undead has died!");
//			player.addSouls(50);
			return new DoNothingAction();
		}
		// TODO: Hammad I added this line cause the monsters kept tracking through the different maps u can remove it if u find a way to stop tehm from tracking the player
		if(!map.contains(player)){
			return new DoNothingAction();
		}
		Location here = map.locationOf(this);
		Location there = map.locationOf(player);
		int currentDistance = distance(here, there);
		if (currentDistance <= 1) {
			for (Exit exit : here.getExits()) {
				if (exit.getDestination() == there) {
					return new AttackAction(player, "North");
				}
			}
		}
		if (currentDistance <= 2) {
			for (Exit exit : here.getExits()) {
				if (exit.getDestination() == there) {
					behaviours.remove(0);
					behaviours.add(new FollowBehaviour(player));
				}
			}
		}
		else{
			behaviours.remove(0);
			behaviours.add(new WanderBehaviour());
			}
//		if (distance(map.locationOf(this), map.locationOf(player)) <= 2) {
//			behaviours.remove(0);
//			behaviours.add(new FollowBehaviour(player));
//		} else {
//			behaviours.remove(0);
//			behaviours.add(new WanderBehaviour());
//		}
		for (Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		if(rand.nextInt(10) == 5){
			map.removeActor(this);
		}
		return new DoNothingAction();
	}

	/**
	 * This method finds the distance between a and b
	 * @param a location of a which is an Actor
	 * @param b location of b which is an Actor
	 * @return the distance between a and b
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return a collection of actions
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this, direction));
		}
		return actions;
	}

	/**
	 *
	 * @return skeletons hit points and max hit points
	 */
	@Override
	public String toString() {
		return String.format("Undead (%d/%d)",this.hitPoints,this.maxHitPoints);
	}

}

