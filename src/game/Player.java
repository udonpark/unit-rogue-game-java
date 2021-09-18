package game;

import edu.monash.fit2099.engine.*;
import game.bonfire.BonfireAction;
import game.enums.Abilities;
import game.enums.Status;
import game.estusFlask.EstusFlask;
import game.estusFlask.EstusFlaskAction;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private EstusFlask estus;
	private EstusFlaskAction estusAction;
	private BonfireAction bonfireAction;
	private int currentSouls;
	private VendorAction vendorAction;
	private selfharmAction selfharm;
	private selfDeath death;

	private int lastBonfireX = 38, lastBonfireY = 12;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
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
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		int[] playerLocation = {map.locationOf(this).x() , map.locationOf(this).y()};

		actions.add(selfharm);
		actions.add(death);

		if(!this.isConscious()){
			return new playerDeathAction();
		}

		if (hasCapability(Abilities.DRINK)){
			actions.add(estusAction);
		}

		if ((playerLocation[0]-38 + Math.abs(playerLocation[1] - 11) <= 1) && hasCapability(Abilities.REST)){
			actions.add(bonfireAction);
		}

		if ((playerLocation[0]-37 + Math.abs(playerLocation[1] - 11) <= 1) && hasCapability(Abilities.BUY)){
			actions.add(vendorAction);
		}
//		System.out.println(map.at(38,11).getGround());
//		System.out.println(playerLocation[0] + " " + playerLocation[1]);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		System.out.println(printStatus());
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void initializeInstanceSouls() {
		currentSouls = 0;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
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
		// to fix: initilize weapon to broadsword
		return String.format("Unkindled (%d/%d), holding %s, %d souls",hitPoints,maxHitPoints,"weapon",currentSouls);

	}

	@Override
	public void resetInstance() {
		this.heal(maxHitPoints);
	}

	@Override
	public boolean isExist() {
		return true;
	}
}
