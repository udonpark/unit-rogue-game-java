package weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.skills.SpinAttackAction;

public class GiantAxe extends WeaponItem {
    public GiantAxe(){
        super("Giant Axe", 'G', 50, "hits", 80);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {  // It does not have a target, but a ranged attack
        return new SpinAttackAction(this);
    }


}
