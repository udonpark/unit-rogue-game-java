package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.estusFlask.EstusFlask;

public class BonfireAction extends Action {

    private Actor player;
    private EstusFlask estusFlask;
    private ResetManager resetManager;

    public BonfireAction(Actor player, EstusFlask estusFlask) {
        this.player = player;
        this.estusFlask = estusFlask;
        resetManager = ResetManager.getInstance();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        estusFlask.setCharges(estusFlask.getMaxCharges());
//        actor.heal(99999);
        resetManager.run();
        return "Player rested";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest at Firelink Bonfire";
    }

    @Override
    public String hotkey() {
        return "e";
    }
}
