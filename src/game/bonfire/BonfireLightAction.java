package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class BonfireLightAction extends Action {
    private Bonfire bonfire;
    private Actor actor;

    /**
     * Constructor for the bonfire light action
     * @param actor refers to the actor using the action
     * @param bonfire refers to the bonfire that the actor is standing next to or on
     */
    public BonfireLightAction(Actor actor, Bonfire bonfire ) {

        this.actor = actor;
        this.bonfire = bonfire;
    }

    /**
     * The function to light a bonfire
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to indicate that the action was successful and the bonfire is lit
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Bonfire.getBonfires().add(bonfire);
        ((Player) actor).setLastBonfireX(map.locationOf(actor).x()); ((Player) actor).setLastBonfireY(map.locationOf(actor).y());
        ((Player) actor).setLastMap(map);
        bonfire.lightBonfire();
        return bonfire.getName() + "lit";
    }

    /**
     * Method that displays a message describing the action to be undertaken
     * @param actor The actor performing the action.
     * @return a message describing the action to be undertaken
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Light " + bonfire.getName();
    }
}
