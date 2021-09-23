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
	private BonfireAction bonfireAction;
	private int currentSouls;

	private VendorActionHP vendorActionHP;
	private VendorActionBS vendorActionBS;
	private VendorActionGA vendorActionGA;

	private PlayerDeathAction playerDeath;
	private selfharmAction selfharm;
	private selfDeath death;
	private Location prevLocation = null;
//	private WeaponItem playerWeapon;

	private int lastBonfireX = 38, lastBonfireY = 11;
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
		initializeInstanceSouls();
		registerInstance();

		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DRINK);
		this.addCapability(Abilities.BUY);

		this.estus = new EstusFlask(3,3);
		this.estusAction = new EstusFlaskAction(estus,maxHitPoints);
		this.bonfireAction = new BonfireAction(this,estus);
		this.selfharm = new selfharmAction();
		this.death = new selfDeath();
		this.vendorActionHP = new VendorActionHP(this);
		this.vendorActionBS = new VendorActionBS(this);
		this.vendorActionGA = new VendorActionGA(this);
		this.playerDeath = new PlayerDeathAction(this);
		this.inventory.add(new BroadSword(this));

	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		int[] playerLocation = {map.locationOf(this).x() , map.locationOf(this).y()};
		actions.add(selfharm);
		actions.add(death);
		if(!this.isConscious() ||  map.locationOf(this).getGround().toString().equals("Valley")){

			playerDeath.setLastLocation(prevLocation);
			return playerDeath;
		}

		if (hasCapability(Abilities.DRINK)){
			actions.add(estusAction);
		}

		if (map.at(playerLocation[0],playerLocation[1]).getGround().toString().equals("Bonfire") && hasCapability(Abilities.REST)){
			actions.add(bonfireAction);
		}

		if (map.at(playerLocation[0],playerLocation[1]).getGround().toString().equals("Vendor") && hasCapability(Abilities.BUY)){
			if (this.currentSouls >= 200){
				actions.add(vendorActionHP);
			}
			if (this.currentSouls >= 500){
				actions.add(vendorActionBS);
				actions.add(vendorActionGA);
			}

		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		display.println(printStatus());
		estusAction.setActorMaxHitPoints(maxHitPoints);
		prevLocation = map.locationOf(this);
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void initializeInstanceSouls() {
		currentSouls = 1500;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
		soulObject.addSouls(currentSouls);
		this.subtractSouls(currentSouls);
	}

	@Override
	public boolean addSouls(int souls) {
		if (souls > 0){
			currentSouls += souls;
			return true;
		}
		return false;

	}

	@Override
	public boolean subtractSouls(int souls) {
		if (souls > 0 ){
			currentSouls -= souls;
			if (currentSouls < 0){
				currentSouls = 0;
			}
			return true;
		}
		return false;
	}

	public String printStatus() {
		return String.format("Unkindled (%d/%d), holding %s, %d souls",hitPoints,maxHitPoints,getWeapon(),currentSouls);

	}

	public int getSouls(){
		return this.currentSouls;
	}

	@Override
	public void resetInstance() {
		this.heal(maxHitPoints);
	}

	@Override
	public boolean isExist() {
		return true;
	}

	public int getLastBonfireX() {
		return lastBonfireX;
	}

	public void setLastBonfireX(int lastBonfireX) {
		this.lastBonfireX = lastBonfireX;
	}

	public int getLastBonfireY() {
		return lastBonfireY;
	}

	public void setLastBonfireY(int lastBonfireY) {
		this.lastBonfireY = lastBonfireY;
	}


}
