package weapon;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.Player;
import game.skills.ChargeAction;
import game.skills.WindSlashAction;

public class StormRuler extends WeaponItem {
    private int charge;
    private Player holder;
//    private Actions actions;

    public StormRuler(Player player){
        super("Storm Ruler", 'S', 70, "hits", 70);
        this.charge = 0;
        this.holder = player;
//        this.actions = new Actions();
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (this.charge == 3){
            return new WindSlashAction(this);
        }
        if (this.charge < 3){
            return new ChargeAction(this);
        }
        return null;
    }

    public int getCharge(){
        return this.charge;
    }

    public void increaseCharge(){
        this.charge++;
    }

    public void resetCharge(){
        this.charge = 0;
    }
}
