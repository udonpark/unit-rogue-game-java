package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class selfharmAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.hurt(20);
        return "Unkindled cut himself";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled cuts himself for instagram clout";
    }

    @Override
    public String hotkey() {
        return "n";
    }
}
