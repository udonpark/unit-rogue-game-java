package weapon;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.WeaponItem;
import game.Enemies.YhormTheGiant;
import game.enums.Status;
import game.skills.EmberFormAction;

import java.util.List;

public class YhormsGreatMachete extends WeaponItem {
    private YhormTheGiant yhorm;

    /**
     * Constructor for YhormsGreatMachete
     * @param yhorm the yhorm who is holding Machete, as only yhorm could hold this weapon
     */
    public YhormsGreatMachete(YhormTheGiant yhorm){
        super("Yhorm's Great Machete", 'Y', 95, "hits", 60);
        this.yhorm = yhorm;
    }

    /**
     * Returns all allowable actions, which is EmberFormAction if the status is in RAGE_MODE.
     * @return List of possible actions by Machete
     */
    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        if (this.yhorm.hasCapability(Status.RAGE_MODE)){
            actions.add(new EmberFormAction(this));
        }
        return actions.getUnmodifiableActionList();
    }

    /**
     * Overrides method that gets chance of hitting. This changes when Yhorm is in rage mode or not
     * @return 90 if rage mode, 60 otherwise
     */
    @Override
    public int chanceToHit(){
        if (this.yhorm.hasCapability(Status.RAGE_MODE)){
            return 90;
        }
        else{
            return super.chanceToHit();
        }
    }
}
