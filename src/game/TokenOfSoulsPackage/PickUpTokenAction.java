package game.TokenOfSoulsPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.interfaces.Soul;

public class PickUpTokenAction extends PickUpItemAction {

    private TokenOfSouls token;

    public PickUpTokenAction(Item item) {
        super(item);
        this.token = (TokenOfSouls) item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("hello test");
        token.transferSouls((Soul) actor);
        map.locationOf(actor).removeItem(item);
        return menuDescription(actor);
    }
}
