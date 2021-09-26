package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * An action to swap weapon (new weapon replaces old weapon).
 * It loops through all items in the Actor inventory.
 * The old weapon will be gone.
 * TODO: you may update this code if required.
 */
public class SwapWeaponAction extends PickUpItemAction {

    /**
     * Constructor for the Action which swaps current holding weapon of an actor to any of the
     * other wanted weapon
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public SwapWeaponAction(Item weapon){
        super(weapon);
    }

    /**
     * Execute function which deletes currentWeapon holding in the inventory, and instead adds
     * the new weapon which is stored as this.item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu if swapping has been successful
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon currentWeapon = actor.getWeapon();
        List<Item> items = actor.getInventory();

        // loop through all inventory
        for(Item item : items){
            if(item.asWeapon() != null){
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }

        // if the ground has item, remove that item.
        // additionally, add new weapon to the inventory (equip).
        super.execute(actor, map);
        return actor + " swaps " + currentWeapon + " with " + item;
    }

}
