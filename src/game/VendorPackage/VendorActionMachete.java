package game.VendorPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
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
     * Action class for player to purchase increase Machete from Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when it is purchased
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swap = new SwapWeaponAction(new YhormsGreatMachete(actor));
        swap.execute(actor, map);
        this.player.subtractSouls(500); // TO BE CHANGED
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