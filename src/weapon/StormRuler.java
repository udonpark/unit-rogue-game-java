package weapon;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.Player;
import game.SwapWeaponAction;
import game.skills.ChargeAction;
import game.skills.WindSlashAction;

import java.util.List;
import java.util.Random;

public class StormRuler extends WeaponItem {
    private int charge;
    private Player holder;

    public StormRuler(){
        super("Storm Ruler", '7', 70, "hits", 70);
        this.charge = 0;
        this.holder = null;
    }

    /**
     *
     * @return
     */

    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        if (holder != null) {
            if (this.charge == 3) {
                actions.add(new WindSlashAction(this));
            }
            if (this.charge < 3) {
                actions.add(new ChargeAction(this));
            }

        }
        return actions.getUnmodifiableActionList();
    }

    @Override
    public int damage() {
        int dmg = super.damage();
//        for (Exit exits: this.holder)
//        if (this.holder.
        if (new Random().nextInt(4) == 0) {
            return dmg * 2;
        }
        else{
            return dmg;
        }
    }

//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map){
//        Actions actions = new Actions();
//        if (this.charge == 3){
//            actions.add(new WindSlashAction(this));
//        }
//        if (this.charge < 3){
//            actions.add(new ChargeAction(this));
//        }
//        return actions;
//    }

//    @Override
//    public WeaponAction getActiveSkill(Actor target, String direction) {
//        if (this.charge == 3){
//            return new WindSlashAction(this);
//        }
//        if (this.charge < 3){
//            return new ChargeAction(this);
//        }
//        return null;
//    }

    public int getCharge(){
        return this.charge;
    }

    public void increaseCharge(){
        this.charge++;
    }

    public void resetCharge(){
        this.charge = 0;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        holder = (Player) actor;
        //swap action
//        return super.getPickUpAction(actor);
        return new SwapWeaponAction(this);
    }


    @Override
    public String toString() {
        return "StormRuler";
    }
}
