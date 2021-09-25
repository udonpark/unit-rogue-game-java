package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour {

    private Actor target;

    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        if (currentDistance <= 1) {
            for (Exit exit : here.getExits()) {
                if(exit.getDestination() == there){
                    return new AttackAction(target, "North");
                }
            }
        }
        return null;
    }


    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
