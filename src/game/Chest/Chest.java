package game.Chest;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.Enemies.Enemies;
import game.Player;
import game.enums.Status;
import game.ground.fogwall.FogWallAction;
import game.interfaces.Resettable;

import java.util.ArrayList;

public class Chest extends Enemies   {
    /**
     * Constructor.
     *
     */
    private Location location;

    /**
     *
     * @param name
     * @param x
     * @param y
     * @param Map
     */
    public Chest(String name, int x, int y, GameMap Map) {
        super(name, '?', 0);
        location = new Location(Map,x,y);
    }

    /**
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor instanceof Player) {
            actions.add(new ChestAction(this));

        }
        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     * It stays at its place so does nothing
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

}
