package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.CindersOfLord.CinderOfAldrich;
import game.CindersOfLord.CinderOfYhorm;
import game.Enemies.AldrichTheDevourer;
import game.Enemies.Skeleton;
import game.Enemies.Undead;
import game.Enemies.YhormTheGiant;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				if (!(target instanceof Player)) {
					dropActions.add(item.getDropAction(actor));
				}
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
			if (!(target instanceof Player)) {
				if (target instanceof Undead) {
					((Player) actor).addSouls(50);
				}
				if (target instanceof Skeleton) {

					((Player) actor).addSouls(250);
					Random rand = new Random();
					if (rand.nextInt(2) == 1 && !target.hasCapability(Status.WAS_REVIVED)) {
						target.heal(100);
						target.addCapability(Status.WAS_REVIVED);
						result += System.lineSeparator() + "skeleton was revived";
						return result;
					} else {
						map.removeActor(target);
					}
				}
				if (target instanceof YhormTheGiant) {
					((Player) actor).addCapability(Status.KILLED_YHORM);
					Location yhorm = map.locationOf(target);
					map.removeActor(target);
					yhorm.addItem(new CinderOfYhorm());
					((Player) actor).addSouls(5000);
				}

				if (target instanceof AldrichTheDevourer) {
					((Player) actor).addCapability(Status.KILLED_ALDRICH);
					Location aldrich = map.locationOf(target);
					map.removeActor(target);
					aldrich.addItem(new CinderOfAldrich());
					((Player) actor).addSouls(5000);
				}
				result += System.lineSeparator() + target + " is killed.";
			}

			return result;
		}
		return result;
	}
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
