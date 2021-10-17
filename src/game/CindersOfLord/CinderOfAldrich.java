package game.CindersOfLord;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;

public class CinderOfAldrich extends CindersOfLord {

    /***
     * Constructor for Cinder of Lord which correspond to Aldrich
     */
    public CinderOfAldrich() {
        super("CinderOfLord(Aldrich)", 'C');
    }

    /**
     * picks up cinder by player
     * @param actor an actor that will interact with this item
     * @return action to pick the item
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }
}
