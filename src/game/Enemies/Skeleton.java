package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import weapon.BroadSword;
import weapon.GiantAxe;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Actor implements Resettable {
    /**
     *
     */
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private WeaponItem BroadSword = new BroadSword(this);
    private WeaponItem GiantAxe = new GiantAxe(this);
    private Player player;
    private int initialx, initialy;


    /**
     * constructor for the Skeleton class
     * @param name The name of the Skeleton
     * @param x initial x coordinate of where Skeleton stands
     * @param y initial y coordinate of where Skeleton stands
     */
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


    /**
     * Figure out what to do next.
     * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    /**
     * Overrides play turn method
     * Called every turn and executes an action, if player is in adjacent blocks it attacks. if player is 2 blacks away
     * it follows the player, else it just wanders around
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        if (!this.isConscious()) {
//            int counter = 0;
//            Random rand = new Random();
//            if (rand.nextInt(2) == 0 && counter < 1){
//                this.heal(100);
//                counter ++;
//            }
//            else {
//                map.removeActor(this);
//                player.addSouls(250);
//            }
        if(!map.contains(player)){
            return new DoNothingAction();
        }
        Location here = map.locationOf(this);
        Location there = map.locationOf(player);
        int currentDistance = distance(here, there);
        if (currentDistance <= 1) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination() == there) {
                    return new AttackAction(player, "");
                }
            }
        }
// reuse exit from the engine class
        if (currentDistance <= 2) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination() == there) {
                    behaviours.remove(0);
                    behaviours.add(new FollowBehaviour(player));
                }
            }
        }
        else {
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

    /**
     * This method finds the distance between a and b
     * @param a location of a which is an Actor
     * @param b location of b which is an Actor
     * @return the distance between a and b
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }


    /**
     * Overrides resetInstance from Resettable
     */
    @Override
    public void resetInstance() {
        GameMap map = Application.getProfaneCapital();
        if (this.isConscious()){
            map.moveActor(this, map.at(initialx,initialy));
        }else{
            this.heal(100);
            map.at(initialx,initialy).addActor(this);
        }

    }

    /**
     * Overiddes isExist
     * @return the existence of the instance in the game.
     */
    @Override
    public boolean isExist() {
        return true;
    }


    /**
     * Overiddes getAllowableActions Method
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     *
     * @return Skeletons hit points and max hit points as a String
     */
    @Override
    public String toString() {
        return String.format("Skeleton (%d/%d)",this.hitPoints,this.maxHitPoints);
    }


    /**
     * If hit points after attack are lesser than zero than it makes hit points = 0, as Enemy can't have negative
     * hit points
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        if((hitPoints - points) < 0){
            hitPoints = 0;
        }
        else {
            hitPoints -= points;
        }
    }
}
