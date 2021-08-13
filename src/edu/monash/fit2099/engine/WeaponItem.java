package edu.monash.fit2099.engine;

/**
 * Class representing items that can be used as a weapon.
 */
public abstract class WeaponItem extends Item implements Weapon{

	protected int damage;
	protected int hitRate;
	protected String verb;

	/** Constructor.
	 *
	 * @param name name of the item
	 * @param displayChar character to use for display when item is on the ground
	 * @param damage amount of damage this weapon does
	 * @param verb verb to use for this weapon, e.g. "hits", "zaps"
	 * @param hitRate the probability/chance to hit the target.
	 */
	public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
		super(name, displayChar, true);
		this.damage = damage;
		this.verb = verb;
		this.hitRate = hitRate;
	}

	/**
	 * Accessor for damage done by this weapon.
	 *
	 * @return the damage
	 */
	public int damage() {
		return damage;
	}

	/**
	 * Returns the verb used for attacking with this weapon, so that it can be displayed
	 * in the UI.
	 *
	 * @return a third-person present tense verb, e.g. "hits", "zaps"
	 */
	public String verb() {
		return verb;
	}


	/**
	 * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
	 * @return Integer, such as
	 */
	public int chanceToHit(){return hitRate;}

}
