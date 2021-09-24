package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class selfDeath extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.hurt(100);
        return "Unkindled has died lol";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Kill unkindled lmao";
    }

    @Override
    public String hotkey() {
        return "m";
    }
}
