package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Application;

import java.util.ArrayList;

public class BonfireTravelAction extends Action {

    private Bonfire bonfire;
    public BonfireTravelAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        GameMap destination = bonfire.getLocation().map();
        map.moveActor(actor,destination.at(bonfire.getLocation().x(),bonfire.getLocation().y()));
        return "Travelled to " + bonfire.getName();
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Traverse to " + bonfire.getName();

    }

    @Override
    public String hotkey() {
        return super.hotkey();
    }
}
