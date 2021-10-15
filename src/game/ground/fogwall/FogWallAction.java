package game.ground.fogwall;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;
import game.Player;

public class FogWallAction extends Action {
    /**
     * function that allows player to traverse the fogwall
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string message to indicate success
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            if (Application.getProfaneCapital().contains(actor)) {
                map.moveActor(actor, Application.getAnorLondoMap().at(map.locationOf(actor).x(), 0));
            } else {
                map.moveActor(actor, Application.getProfaneCapital().at(map.locationOf(actor).x(), 25));
            }
        }
        return "Traversed!";
    }
    /**
     * Method that displays a message describing the action to be undertaken
     * @param actor The actor performing the action.
     * @return a message describing the action to be undertaken
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Traverse Fog Wall";
    }
    /**
     * Hotkey to undertake action
     * @return Hotkey to undertake action
     */
    @Override
    public String hotkey() {
        return "y";
    }
}
