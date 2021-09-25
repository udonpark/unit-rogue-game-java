package weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import java.util.Random;

public class BroadSword extends WeaponItem {
    private Actor holder;
    public BroadSword(Actor holder) {
        super("BroadSword", 'B', 30, "hits", 80);
        this.holder = holder;
    }

    @Override
    public int damage() {
        if (new Random().nextInt(4) == 0) {
            return super.damage() * 2;
        }
        else{
            return super.damage();
        }
    }
}
