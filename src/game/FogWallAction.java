package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FogWallAction extends Action {
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

    @Override
    public String menuDescription(Actor actor) {
        return "Traverse Fog Wall";
    }

    @Override
    public String hotkey() {
        return "y";
    }
}
