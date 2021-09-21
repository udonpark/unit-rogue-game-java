package game.Vendor;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Vendor extends Ground {
    public Vendor() {
        super('F');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}

