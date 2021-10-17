package game.Chest;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.enums.Status;
import game.ground.fogwall.FogWallAction;

public class Chest extends Actor {
    /**
     * Constructor.
     *
     */
    private Location location;


    public Chest(String name, int x, int y, GameMap Map) {
        super(name, '?', 0);
        location = new Location(Map,x,y);
    }


    public Location getLocation() {
        return location;
    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.CAN_OPEN_CHEST)) {
            actions.add(new ChestAction(this));
        }
        return actions;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
