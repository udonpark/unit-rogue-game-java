package weapon;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.skills.EmberFormAction;

import java.util.List;
import java.util.Random;

public class DarkmoonLongbow extends WeaponItem {
    private Actor holder;

    /**
     * Constructor for Darkmoon Longbow
     * @param holder the actor who is holding Longbow, both player and Aldrich could hold this weapon
     */
    public DarkmoonLongbow(Actor holder){
        super("Darkmoon Longbow", 'D', 70, "hits", 80);
        this.holder = holder;
    }

    /**
     * Returns all allowable actions, which is EmberFormActionAD if the status is in RAGE_MODE.
     * @return List of possible actions by Longbow
     */
    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        if (this.holder.hasCapability(Status.RAGE_MODE)){
            actions.add(new EmberFormAction(this));
        }
        return actions.getUnmodifiableActionList();
    }

    /**
     * damage method, a getter for damage. Has 15% to deal double damage
     * @return the damage to be dealt by this weapon
     */
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
