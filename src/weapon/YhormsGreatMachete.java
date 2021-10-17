package weapon;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import game.Enemies.YhormTheGiant;
import game.Player;
import game.enums.Status;
import game.skills.EmberFormAction;

import java.util.List;

public class YhormsGreatMachete extends WeaponItem {
    private Actor holder;

    /**
     * Constructor for YhormsGreatMachete
     * @param holder the actor who is holding Machete, both player and yhorm could hold this weapon
     */
    public YhormsGreatMachete(Actor holder){
        super("Yhorm's Great Machete", 'Y', 95, "hits", 60);
        this.holder = holder;
    }

    /**
     * Returns all allowable actions, which is EmberFormAction if the status is in RAGE_MODE.
     * @return List of possible actions by Machete
     */
    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        if (this.holder.toString().equals("Unkindled")){ // player can use the ability anytime
            actions.add(new EmberFormAction(this));
        }
        else if (this.holder.toString().equals("Yhorm The Giant")){
            if (this.holder.hasCapability(Status.RAGE_MODE)){
                actions.add(new EmberFormAction(this));
            }
        }

        return actions.getUnmodifiableActionList();
    }

    /**
     * Overrides method that gets chance of hitting. This changes when Yhorm is in rage mode or not
     * @return 90 if rage mode, 60 otherwise
     */
    @Override
    public int chanceToHit(){
        if (this.holder.hasCapability(Status.RAGE_MODE)){
            return 90;
        }
        else{
            return super.chanceToHit();
        }
    }
}
