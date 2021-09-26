package weapon;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.SwapWeaponAction;
import game.skills.SpinAttackAction;

import java.util.List;

public class GiantAxe extends WeaponItem {
    private Actor holder;

    /**
     * Constructor for Giant Axe weapon, to be held by skeleton or player
     * @param holder Actor, holding this weapon
     */
    public GiantAxe(Actor holder){
        super("Giant Axe", 'G', 50, "hits", 80);
        this.holder = holder;
    }

    // As recommended, we did not use getActiveSKill yet in assignment 2
//    @Override
//    public WeaponAction getActiveSkill(Actor target, String direction) {  // It does not have a target, but a ranged attack
//        return new SpinAttackAction(this);
//    }

    /**
     * Returns list of special WeaponActions
     * @return List of actions which are possible by this weapon currently
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(new SpinAttackAction(this));
        return actions.getUnmodifiableActionList();
    }

    /**
     *
     * @param actor an actor that will interact with this item (pick up)
     * @return SwapWeaponAction(), that swaps this Giant Axe from the actor inventory
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (!(actor.toString().equals("Unkindled"))){
            return null;
        }
        holder = (Player) actor;
        return new SwapWeaponAction(this);
    }
}
