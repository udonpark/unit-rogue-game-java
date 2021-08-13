package edu.monash.fit2099.demo.mars;

import java.util.*;

import game.interfaces.Behaviour;
import edu.monash.fit2099.engine.*;


public class Bug extends Actor implements Breathing {

	private Random rand = new Random();
	private int oxygenLevel;

	/**
	 * 	Bug behaviours.
	 * 	It is exposed to connect it with Player instance.
	 * 	We prefer to have it private in the assignment :).
	 */
	List<Behaviour> actionFactories = new ArrayList<>();


	public Bug() {
		super("Feature", 'x', 1);
		oxygenLevel = 40;
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		oxygenLevel -= 1;
		for (Behaviour factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new KickAction(this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
	}

	@Override
	public String toString(){
		return name + "(oxygen: " + oxygenLevel +")";
	}

	/**
	 * Transfer current oxygen to another breathing entity.
	 * This implementation hides "getter of oxygenLevel" and make it as a `behaviour` method.
	 * @param breathing in this scenario, it is a Player
	 */
	@Override
	public void transferOxygen(Breathing breathing) {
		breathing.addOxygen(oxygenLevel);
	}
}
