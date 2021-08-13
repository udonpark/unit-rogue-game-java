package edu.monash.fit2099.engine;

/**
 * Class that represents a weapon for an unarmed Actor (e.g. fists, claws, etc.)
 *
 * @see Weapon
 */
public class IntrinsicWeapon implements Weapon{

	private int damage;
	private String verb;
	private int hitRate;

	public IntrinsicWeapon(int damage, String verb) {
		this.damage = damage;
		this.verb = verb;
		this.hitRate = 50; // 50% chance to hit
	}

    /**
     * The amount of damage the Weapon will inflict
     *
     * @return the damage, in hitpoints
     */
	@Override
	public int damage() {
		return damage;
	}

    /**
     * A verb to use when displaying the results of attacking with this Weapon
     *
     * @return String, e.g. "punches", "zaps"
     */
	@Override
	public String verb() {
		return verb;
	}

	/**
	 * An integer of how many percent (as dividend/100) the Actor can hit with this weapon.
	 * @return the chance to hit.
	 */
	@Override
	public int chanceToHit() {
		return hitRate;
	}
}
