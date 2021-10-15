package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.skills.EmberFormAction;
import weapon.DarkmoonLongbow;
import weapon.YhormsGreatMachete;

import java.util.ArrayList;

public class AldrichTheDevourer extends LordOfCinder implements Resettable {
    private Player player;
    private WeaponItem DarkmoonLongbow = new DarkmoonLongbow(this);
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private int initialx,initialy;
    /**
     * Constructor.
     */
    public AldrichTheDevourer(int x, int y) {
        super("Aldrich The Devourer", 'A', 350);
        this.player = Application.getPlayer();
        super.addItemToInventory(DarkmoonLongbow);
//        this.addCapability(Status.WEAK_TO_STORM_RULER);
        this.initialx = x;
        this.initialy = y;
        registerInstance();

    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to be performed by Aldrich
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            map.removeActor(this);
            player.addSouls(5000);
        }
        System.out.printf("Aldrich's HP: (%d/%d)\n", this.hitPoints, this.maxHitPoints);
        if (this.hasCapability(Status.STUNNED)) {
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }
//        if (this.hitPoints < this.maxHitPoints / 2) {
//            this.addCapability(Status.RAGE_MODE);
//            System.out.println("Yhorm in RAGE MODE!");
//            return new EmberFormAction(DarkmoonLongbow);
//        }
        if (distance(map.locationOf(this), map.locationOf(player)) <= 1) {
//            int damage = inventory.get(0).asWeapon().damage();
//            player.hurt(damage);
            return new AttackAction(player, "");
        }
        if (distance(map.locationOf(this), map.locationOf(player)) <= 2) {
            behaviours.add(new FollowBehaviour(player));
        }else if (behaviours.size() > 0){
            map.moveActor(this, map.at(initialx,initialy));
            for (int i  = 0; i < behaviours.size();i++){behaviours.remove(i);}}

        for (Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();

    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions
     */
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

    /**
     *
     * @param a
     * @param b
     * @return
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Aldrich The Devourer";

    }

    /**
     *
     */
    @Override
    public void resetInstance() {
        GameMap map = Application.getProfaneCapital();
        if (this.isConscious()){
            this.heal(500);
//            System.out.println("test");
            map.moveActor(this, map.at(initialx,initialy));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isExist() {
        return true;
    }
}
