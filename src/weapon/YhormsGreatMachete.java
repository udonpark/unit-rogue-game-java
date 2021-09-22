package weapon;

import edu.monash.fit2099.engine.WeaponItem;
import game.YhormTheGiant;

public class YhormsGreatMachete extends WeaponItem {
    private YhormTheGiant yhorm;
    public YhormsGreatMachete(YhormTheGiant yhorm){
        super("Yhorm's Great Machete", 'Y', 95, "hits", 60);
        this.yhorm = yhorm;
    }
}
