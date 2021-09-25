package weapon;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.WeaponItem;
import game.YhormTheGiant;
import game.enums.Status;
import game.skills.EmberFormAction;

import java.util.List;

public class YhormsGreatMachete extends WeaponItem {
    private YhormTheGiant yhorm;
    public YhormsGreatMachete(YhormTheGiant yhorm){
        super("Yhorm's Great Machete", 'Y', 95, "hits", 60);
        this.yhorm = yhorm;
    }

    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        if (this.yhorm.hasCapability(Status.RAGE_MODE)){
            actions.add(new EmberFormAction(this));
        }
        return actions.getUnmodifiableActionList();
    }

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
