package edu.monash.fit2099.engine;

/**
 * Interface for weapon items.
 *
 * As well as providing methods needed by weapons, this interface is used in Item to
 * determine whether an item can be used as a weapon.
 */
public interface Weapon {

	/**
	 * The amount of damage the Weapon will inflict
	 *
	 * @return the damage, in hitpoints
	 */
	int damage();

	/**
	 * A verb to use when displaying the results of attacking with this Weapon
	 *
	 * @return String, e.g. "punches", "zaps"
	 */
	String verb();

	/**
	 * A chance to hit the target (this is a dividend part of percentage)
	 *
	 * @return the chance, in integer for probability with nextInt(), e.g 100 = 100% hit rate
	 */
	int chanceToHit();


	/**
	 * Get an action or skill from the weapon that will be used against one target.
	 * This method allows weapon instance to interact with Actor class.
	 * It can be used to have actionable special attack, heal, silence, etc. to a target
	 *
	 * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
	 * @param target the target actor
	 * @param direction the direction of target, e.g. "north"
	 * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
	 */
	default WeaponAction getActiveSkill(Actor target, String direction){
		return null;
	}
}
