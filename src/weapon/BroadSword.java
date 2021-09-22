package weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;

public class BroadSword extends WeaponItem {
    private Actor holder;
    public BroadSword(Actor holder) {
        super("BroadSword", 'B', 30, "hits", 80);
        this.holder = holder;
    }

}
