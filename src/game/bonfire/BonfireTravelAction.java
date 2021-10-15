package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;

import java.util.ArrayList;

public class BonfireTravelAction extends Action {

    private Bonfire bonfire;
    public BonfireTravelAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    /**
     * Function to allow player to travel between bonfires
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message that indicates the location travelled to by the player
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        GameMap destination = bonfire.getLocation().map();
        map.moveActor(actor,destination.at(bonfire.getLocation().x(),bonfire.getLocation().y()));
        return "Travelled to " + bonfire.getName();
    }

    /**
     * Method that displays a message describing the action to be undertaken
     * @param actor The actor performing the action.
     * @return a message describing the action to be undertaken
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Traverse to " + bonfire.getName();

    }
}
