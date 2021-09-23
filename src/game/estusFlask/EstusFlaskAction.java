
package game.estusFlask;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EstusFlaskAction extends Action {
    private EstusFlask estus;
    private int actorMaxHitPoints;

    /**
     * Constructor for Estus Flask Action class
     * @param estus the estus flask referred to
     * @param maxHitPoints the maximum health of the player
     */
    public EstusFlaskAction(EstusFlask estus,int maxHitPoints) {
        this.estus = estus;
        this.actorMaxHitPoints = maxHitPoints;
    }

    /**
     * Method to execute action functionality
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message to indicate if actor was able to successfully heal
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "Actor healed";
        if (estus.getCharges() < 1){

            message = "No Estus charges left";
        }
        else {
            estus.setCharges(estus.getCharges() - 1);
            actor.heal((int) (actorMaxHitPoints * 0.4));

        }
        return message;
    }
    /**
     * Method that displays a message describing the action to be undertaken
     * @param actor The actor performing the action.
     * @return a message describing the action to be undertaken
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Unkindled drinks Estus Flask (%d/%d)",estus.getCharges(),estus.getMaxCharges());
    }

    /**
     * setter for the max hit points of the estus class used to calculate the amount of hp restored
     * @param actorMaxHitPoints integer representing the maximum actor hitpoints
     */
    public void setActorMaxHitPoints(int actorMaxHitPoints) {
        this.actorMaxHitPoints = actorMaxHitPoints;
    }
    /**
     * Hotkey to undertake action
     * @return Hotkey to undertake action
     */
    @Override
    public String hotkey() {
        return "a";
    }
}
