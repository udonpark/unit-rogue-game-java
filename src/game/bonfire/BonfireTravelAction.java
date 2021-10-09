package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;

public class BonfireTravelAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        // TODO: check if bonfire is lit before allowing travel
        if (Application.getProfaneCapital().contains(actor)) {
            map.moveActor(actor, Application.getAnorLondoMap().at(77, 4));
        } else {
            map.moveActor(actor, Application.getProfaneCapital().at(38, 12));
        }
        return "Traversed between bonfires";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Traverse to other bonfire";
    }

    @Override
    public String hotkey() {
        return "q";
    }
}
