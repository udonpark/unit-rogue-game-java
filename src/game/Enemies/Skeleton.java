package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.skills.SpinAttackAction;
import weapon.BroadSword;
import weapon.GiantAxe;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Actor implements Resettable {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private WeaponItem BroadSword = new BroadSword(this);
    private WeaponItem GiantAxe = new GiantAxe(this);
    private Player player;
    private int initialx, initialy;

    public Skeleton(String name, int x, int y) {
        super(name, 's', 100);
        behaviours.add(new WanderBehaviour());
        this.player = Application.getPlayer();
        registerInstance();
        initialx = x;
        initialy = y;
        Random rand = new Random();
        if(rand.nextInt(2) == 1){
            super.addItemToInventory(GiantAxe);
        }
        else{
            super.addItemToInventory(BroadSword);
        }
    }

//    @Override
//    public void addItemToInventory(Item item) {
//        super.addItemToInventory(item);
//        Random rand = new Random();
//        if(rand.nextInt(1) == 0){
//            inventory.add(BroadSword);
//        }
//        else{
//            inventory.add(GiantAxe);
//        }
//    }

    /**
     * Figure out what to do next.
     * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            int counter = 0;
            Random rand = new Random();
            if (rand.nextInt(1) == 0 && counter < 1){
                this.heal(100);
                counter ++;
            }
            else {
                map.removeActor(this);
                player.addSouls(250);
            }

        }
        if(distance(map.locationOf(this), map.locationOf(player)) <= 1){
//            int damage = inventory.get(0).asWeapon().damage();
//            player.hurt(damage);
            return new AttackAction(player,"");
        }

        if (distance(map.locationOf(this), map.locationOf(player)) <= 2) {
            behaviours.remove(0);
            behaviours.add(new FollowBehaviour(player));
        } else {
            behaviours.remove(0);
            behaviours.add(new WanderBehaviour());
        }
        for (Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }


    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    public void resetInstance() {
        GameMap map = Application.getGameMap();
        if (this.isConscious()){
            map.moveActor(this, map.at(initialx,initialy));
        }else{
            this.heal(99999);
            map.at(initialx,initialy).addActor(this);
        }

    }

    @Override
    public boolean isExist() {
        return true;
    }
//    @Override
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        Actions actions = new Actions();
//
//        GiantAxe giantAxe = new GiantAxe(this);
//        actions.add(giantAxe.getAllowableActions());
//        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//        return actions;
//    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public String toString() {
        return "Skeleton";
    }

}
