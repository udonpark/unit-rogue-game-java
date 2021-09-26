package weapon;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.SwapWeaponAction;
import game.skills.SpinAttackAction;

import java.util.List;

public class GiantAxe extends WeaponItem {
    private Actor holder;
    public GiantAxe(Actor holder){
        super("Giant Axe", 'G', 50, "hits", 80);
        this.holder = holder;
    }

//    @Override
//    public WeaponAction getActiveSkill(Actor target, String direction) {  // It does not have a target, but a ranged attack
//        return new SpinAttackAction(this);
//    }

    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(new SpinAttackAction(this));
        return actions.getUnmodifiableActionList();
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (!(actor.toString().equals("Unkindled"))){
            return null;
        }
        holder = (Player) actor;
        return new SwapWeaponAction(this);
    }
}
