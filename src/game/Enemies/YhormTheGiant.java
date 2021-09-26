package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.AttackAction;
import game.LordOfCinder;
import game.Player;
import game.enums.Status;
import game.skills.EmberFormAction;
import weapon.GiantAxe;
import weapon.YhormsGreatMachete;

public class YhormTheGiant extends LordOfCinder {
    private Player player;
    private WeaponItem Yhormsgreatmachete = new YhormsGreatMachete(this);

    /**
     * Constructor.
     *
     */
    public YhormTheGiant() {
        super("YhormTheGiant", 'Y', 500);
        this.player = Application.getPlayer();
        super.addItemToInventory(Yhormsgreatmachete);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            map.removeActor(this);
            player.addSouls(5000);
        }
        System.out.printf("Yhorm's HP: (%d/%d)\n", this.hitPoints, this.maxHitPoints);
        if (this.hasCapability(Status.STUNNED)){
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }
        if(this.hitPoints < this.maxHitPoints/2){
            this.addCapability(Status.RAGE_MODE);
            System.out.println("Yhorm in RAGE MODE!");
            return new EmberFormAction(Yhormsgreatmachete);
        }
        if(distance(map.locationOf(this), map.locationOf(player)) <= 1){
//            int damage = inventory.get(0).asWeapon().damage();
//            player.hurt(damage);
            return new AttackAction(player,"");
        }
        return new DoNothingAction();

    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }



    //    @Override
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        Actions actions = new Actions();
//        YhormsGreatMachete yhormsGreatMachete = new YhormsGreatMachete(this);
//        GiantAxe giantAxe = new GiantAxe(this);
//        actions.add(giantAxe.getAllowableActions());
//        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//        return actions;
//    }
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    public String toString() {
        return "Yhorm The Giant";
    }
}

