package game.TokenOfSoulsPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.interfaces.Soul;

public class PickUpTokenAction extends PickUpItemAction {

    private TokenOfSouls token;

    /**
     * Constructor for token class inherited from item
     * @param item that can be picked up
     */
    public PickUpTokenAction(Item item) {
        super(item);
        this.token = (TokenOfSouls) item;
    }

    /**
     * Method that executes when actor picks up the item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what the actor has done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        token.transferSouls((Soul) actor);
        map.locationOf(actor).removeItem(item);
        return menuDescription(actor);
    }
}
