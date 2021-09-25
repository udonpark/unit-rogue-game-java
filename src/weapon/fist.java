package weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;

public class fist extends WeaponItem {

    private Actor holder;

    public fist(Actor holder) {
        super("first", 'f', 20, "hits", 80);
        this.holder = holder;
    }
}
