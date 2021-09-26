package weapon;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.Player;
import game.SwapWeaponAction;
import game.enums.Status;
import game.skills.ChargeAction;
import game.skills.WindSlashAction;

import java.util.List;
import java.util.Random;

public class StormRuler extends WeaponItem {
    private int charge;
    private Player holder;
//    private int varying_dmg;
    private GameMap map;

    public StormRuler(GameMap map){
        super("Storm Ruler", '7', 70, "hits", 70);
        this.charge = 0;
        this.holder = null;
//        this.varying_dmg = this.damage;
        this.map = map;
    }

//    @Override
//    public WeaponAction getActiveSkill(Actor target, String direction) {
//        if (target.hasCapability(Status.WEAK_TO_STORM_RULER)){
//            this.varying_dmg = this.damage;
//        }
//        else{
//            this.varying_dmg = this.damage / 2;
//        }
//        return null;
//    }

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
        int dmg = this.damage / 2;
        boolean yhorm_found = false;
        if (this.holder != null){
            for (Exit exits: this.map.locationOf(this.holder).getExits()){
                if (exits.getDestination().getActor() != null) {
                    if (exits.getDestination().getActor().hasCapability(Status.WEAK_TO_STORM_RULER) && !yhorm_found) {
                        dmg = dmg * 2;
                        yhorm_found = true;
                    }
                }
            }
        }
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
        if (!(actor.toString().equals("Unkindled"))){
            return null;
        }
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
