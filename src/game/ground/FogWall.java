package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.FogWallAction;
import game.Player;

public class FogWall extends Ground {
    public FogWall() {
        super('=');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new FogWallAction());
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof Player) {
            return true;
        }
        else{ return false;}
    }
}
