package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Chest.Chest;
import game.Player;
import game.ResetManager;
import game.estusFlask.EstusFlask;

public class BonfireAction extends Action {

    private Actor player;
    private ResetManager resetManager;
    private String name;
    /**
     * Constructor for BonfireAction
     * @param player references the actor using the action
     */
    public BonfireAction(Actor player,String name) {
        this.name = name;
        this.player = player;
        resetManager = ResetManager.getInstance();
    }

    /**
     * Executes the main function of the bonfire action class
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string that represents that the action committed by the player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //sets the location of last bonfire to the bonfire currently rested at by the player
        ((Player) player).setLastBonfireX(map.locationOf(player).x()); ((Player) player).setLastBonfireY(map.locationOf(player).y());
        ((Player) player).setLastMap(map);


        resetManager.run();

        return "Player rested";
    }

    /**
     * Method that displays a message describing the action to be undertaken
     * @param actor The actor performing the action.
     * @return a message describing the action to be undertaken
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at " + name;
    }

    /**
     * Hotkey to undertake action
     * @return Hotkey to undertake action
     */
    @Override
    public String hotkey() {
        return "e";
    }



}
