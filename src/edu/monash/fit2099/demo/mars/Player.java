package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Breathing {

	private int oxygen;
	private final Menu menu = new Menu();
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		oxygen = 50;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		oxygen-=1;
		if(oxygen == 0){
			//end the game when Player doesn't have any oxygen left.
			map.removeActor(this);
		}
		// Check if it has previous action or not. If so, execute that last action.
		// Useful when you want to implement chain-actions like sleeping implementation.
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println(name + "(" + hitPoints + "/" + maxHitPoints + "), Oxygen level:" + oxygen);

		return menu.showMenu(this, actions, display);
	}

	/**
	 * Consumes all oxygen and send it to breathable object.
	 * @param breathing an instance/actor that can interact with oxygen.
	 */
	@Override
	public void transferOxygen(Breathing breathing) {
		breathing.addOxygen(oxygen);
		oxygen = 0;
	}

	/**
	 * Increase current oxygen level.
	 * @param oxygenLevel integer of oxygen level.
	 * @return the transaction will always be successful.
	 */
	@Override
	public boolean addOxygen(int oxygenLevel) {
		oxygen += oxygenLevel;
		return true;
	}

}
