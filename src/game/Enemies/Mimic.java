package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.ArrayList;

public class Mimic extends Actor implements Resettable {
    /**
     * Constructor.
     *
     * @param name the name of the Actor

     */

    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private Player player;

    public Mimic(String name) {
        super(name, 'M', 100);
        behaviours.add(new WanderBehaviour());
        this.player = Application.getPlayer();
        registerInstance();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!map.contains(player)){
            return new DoNothingAction();
        }
        Location here = map.locationOf(this);
        Location there = map.locationOf(player);
        int currentDistance = distance(here, there);
        if (currentDistance <= 1) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination() == there) {
                    return new AttackAction(player, "North");
                }
            }
        }
        if (currentDistance <= 2) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination() == there) {
                    behaviours.remove(0);
                    behaviours.add(new FollowBehaviour(player));
                }
            }
        }
        else{
            behaviours.remove(0);
            behaviours.add(new WanderBehaviour());
        }

        return new DoNothingAction();
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    @Override
    public void resetInstance() {

    }

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
    public boolean isExist() {
        return false;
    }
}
