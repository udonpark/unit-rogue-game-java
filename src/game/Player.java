package game;

import edu.monash.fit2099.engine.*;
import game.VendorPackage.VendorActionBS;
import game.VendorPackage.VendorActionGA;
import game.VendorPackage.VendorActionHP;
import game.bonfire.BonfireAction;
import game.enums.Abilities;
import game.enums.Status;
import game.estusFlask.EstusFlask;
import game.estusFlask.EstusFlaskAction;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import weapon.BroadSword;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private EstusFlask estus;
	private EstusFlaskAction estusAction;
	private int currentSouls;

	private PlayerDeathAction playerDeath;
	private Location prevLocation = null;

	private int lastBonfireX = 38, lastBonfireY = 11;
	private GameMap lastMap = Application.getProfaneCapital();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	//TODO: initialize weapon for player
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		resetInstanceSouls();
		registerInstance();

		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.TRAVEL_TO_PROFANE_CAP);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DRINK);
		this.addCapability(Abilities.BUY);
		// this.addCapability(Status.PASSING_THROUGH_WALLS);

		//Initialize Estus Flask and action
		this.estus = new EstusFlask(3, 3);
		this.estusAction = new EstusFlaskAction(estus, maxHitPoints);

		//Initialize the vendor actions available to player
//		this.vendorActionHP = new VendorActionHP(this);
//		this.vendorActionBS = new VendorActionBS(this);
//		this.vendorActionGA = new VendorActionGA(this);

		//In the case of death this action class is executed to handle all death events
		this.playerDeath = new PlayerDeathAction(this);

		//Initializes the inventory of the player, specifically to add its weapon
		this.inventory.add(new BroadSword(this));



	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Retrieve the player location after each turn, useful when processing certain conditions
		int[] playerLocation = {map.locationOf(this).x(), map.locationOf(this).y()};

		// Code for restricting player from attacking. removing this Status will solve, due to how this system works.
		if (this.hasCapability(Status.ATTACK_LIMITED)) {
			this.removeCapability(Status.HOSTILE_TO_ENEMY);
		} else {
			this.addCapability(Status.HOSTILE_TO_ENEMY);
		}


		//Checks if player is dead, either by checking the hp or by checking if they have stepped on a Valley tile.
		if (!this.isConscious() || map.locationOf(this).getGround().toString().equals("Valley")) {
			playerDeath.setLastLocation(prevLocation);
			return playerDeath;
		}

		//Checks if player is allowed to drink estus flask before displaying the option
		if (hasCapability(Abilities.DRINK)) {
			actions.add(estusAction);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		display.println(printStatus());

		// Hp restored by estus depends on max Hp so this is used to update it
		estusAction.setActorMaxHitPoints(maxHitPoints);

		// Tracks the previous tile the player was on
		prevLocation = map.locationOf(this);

		return menu.showMenu(this, actions, display);
	}

	/**
	 * Method used to set a variable within a Soul class that represents its souls to 0
	 */
	@Override
	public void resetInstanceSouls() {
		currentSouls = 0;
	}

	/**
	 * Transfers all souls of the caller to the soul object specified in the parameter
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
		soulObject.addSouls(currentSouls);
		this.subtractSouls(currentSouls);
	}

	/**
	 * Method to increment the number of souls in a soul object
	 *
	 * @param souls number of souls to be incremented.
	 * @return true if successful, false otherwise
	 */
	@Override
	public boolean addSouls(int souls) {
		if (souls > 0) {
			currentSouls += souls;
			return true;
		}
		return false;

	}

	/**
	 * Method to decrement the number of souls in a soul object
	 *
	 * @param souls number souls to be deducted
	 * @return true if successful, false otherwise
	 */
	@Override
	public boolean subtractSouls(int souls) {
		if (souls > 0) {
			currentSouls -= souls;
			if (currentSouls < 0) {
				currentSouls = 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * Method that returns a string showing the player's overall health, weapon and number of souls
	 *
	 * @return string representing various pieces of information about the player
	 */
	public String printStatus() {
		return String.format("Unkindled (%d/%d), holding %s, %d souls", hitPoints, maxHitPoints, getWeapon(), currentSouls);

	}


	/**
	 * Getter for the player's soul/currency variable
	 *
	 * @return integer representing the number of souls
	 */
	public int getSouls() {
		return this.currentSouls;
	}

	/**
	 * Method that resets the player's hit points to full.
	 */
	@Override
	public void resetInstance() {
		this.heal(maxHitPoints * 10);
	}

	/**
	 * Checks if a class exists or not when resetManager is run
	 *
	 * @return true if a class does exist
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * method that returns the last coordinate bonfire rested at
	 *
	 * @return integer representing the x of last bonfire rested at
	 */
	public int getLastBonfireX() {
		return lastBonfireX;
	}

	/**
	 * Method to set a new last bonfire location
	 *
	 * @param lastBonfireX the x value of the new last bonfire location
	 */
	public void setLastBonfireX(int lastBonfireX) {
		this.lastBonfireX = lastBonfireX;
	}

	/**
	 * method that returns the coordinate last bonfire rested at
	 *
	 * @return integer representing the y of last bonfire rested at
	 */
	public int getLastBonfireY() {
		return lastBonfireY;
	}

	/**
	 * Method to set a new last bonfire location
	 *
	 * @param lastBonfireY the y value of the new last bonfire location
	 */
	public void setLastBonfireY(int lastBonfireY) {
		this.lastBonfireY = lastBonfireY;
	}

	@Override
	public String toString() {
		return "Unkindled";
	}

	public GameMap getLastMap() {
		return lastMap;
	}

	public void setLastMap(GameMap lastMap) {
		this.lastMap = lastMap;
	}
}
