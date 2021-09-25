package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.LordOfCinder;
import game.Player;
import game.enums.Status;
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
        if(this.hitPoints <= 250){
            this.addCapability(Status.RAGE_MODE);
        }
        if(distance(map.locationOf(this), map.locationOf(player)) <= 1){
            int damage = inventory.get(0).asWeapon().damage();
            player.hurt(damage);
        }
        return new DoNothingAction();

    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        YhormsGreatMachete yhormsGreatMachete = new YhormsGreatMachete(this);
        GiantAxe giantAxe = new GiantAxe(this);
        actions.add(giantAxe.getAllowableActions());
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        return actions;
    }
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}

