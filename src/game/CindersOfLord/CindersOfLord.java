package game.CindersOfLord;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.PortableItem;

public abstract class CindersOfLord extends PortableItem {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public CindersOfLord(String name, char displayChar) {
        super(name, displayChar);
    }

    /**
     *
     * @param actor an actor that will interact with this item
     * @return an Action that picks up the Cinder to the inventory
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
//        return new PickUpItemAction(this);
    }
}
