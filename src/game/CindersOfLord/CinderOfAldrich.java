package game.CindersOfLord;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;

public class CinderOfAldrich extends CindersOfLord {

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public CinderOfAldrich(String name, char displayChar, boolean portable) {
        super("CinderOfLord(Aldrich)", 'C', true);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }
}
