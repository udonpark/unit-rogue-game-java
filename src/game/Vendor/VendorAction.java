package game.Vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VendorAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy something fomr the vendor";
    }

    @Override
    public String hotkey() {
        return "v";
    }
}