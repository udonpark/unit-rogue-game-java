package game.VendorPackage;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class VendorAction extends Action {
    protected Player player;

    /**
     * This is VendorAction which inherit from Action.
     * @param player takes in playe which performs the action by vendor
     */
    public VendorAction(Player player){
        this.player = player;
    }

    /**
     * Used to execute the action by purchasing in vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to print after that method is executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * This will show as an option in the Player Menu
     * @param actor The actor performing the action.
     * @return String, description for this VendorAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
