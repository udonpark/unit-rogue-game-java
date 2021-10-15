package game.ground.fogwall;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;

public class FogWall extends Ground {
    /**
     * Constructor for the fogwall class
     */
    public FogWall() {
        super('=');
    }

    /**
     * Method that allows interaction between player and fogwall (i.e traversal)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of possible actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new FogWallAction());
        return actions;
    }

    /**
     * Checks if an actor is able to walk on the ground object
     * @param actor the Actor to check
     * @return true if its a player, else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor instanceof Player;
    }
}
