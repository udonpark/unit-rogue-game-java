package game.VendorPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.CindersOfLord.CinderOfYhorm;
import game.Player;
import game.SwapWeaponAction;
import weapon.BroadSword;
import weapon.YhormsGreatMachete;

public class VendorActionMachete extends VendorAction {

    /**
     * Constructor for VendorAction that allows player to purchase Yhorm's Great Machete from vendor.
     * @param player Player, who is performing the action.
     */
    public VendorActionMachete(Player player){
        super(player);
    }

    /**
     * Action class for player to purchase Machete from Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when it is purchased
     */
    public String execute(Actor actor, GameMap map, CinderOfYhorm machete) {
        boolean has_cinder = false;
        for (Item item: this.player.getInventory()) {
            if (item.toString().equals("CinderOfLord(Yhorm)")){
                has_cinder = true;
            }
        }
        if (!has_cinder){
            return "You have defeated Yhorm, but do not have Cinder Of Lord with you!";
        }
        // after making sure that he has cinder, do the trade
        SwapWeaponAction swap = new SwapWeaponAction(new YhormsGreatMachete(actor));
        swap.execute(actor, map);
//        this.player.subtractSouls(500); // TO BE CHANGED
        this.player.removeItemFromInventory(machete); // as a payment
        return "Paid Cinders of Lord and purchased Yhorm's Great Machete";
    }

    /**
     * Shows menuDescription of this class
     * @param actor The actor performing the action.
     * @return String, description of this action to show in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Trade Yhorm's Great Machete for Cinders of Lord from the Vendor";
    }

    /**
     * Player is responsible for typing this string to execute this action
     * @return String, o representing hotkey
     */
    @Override
    public String hotkey() {
        return "m";
    }
}