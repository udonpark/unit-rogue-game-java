package game.CindersOfLord;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

public abstract class CindersOfLord extends Item {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public CindersOfLord(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     *
     * @param actor an actor that will interact with this item
     * @return an Action that picks up the Cinder to the inventory
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }
}
