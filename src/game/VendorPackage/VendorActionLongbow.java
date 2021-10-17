package game.VendorPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.CindersOfLord.CinderOfAldrich;
import game.Player;
import game.SwapWeaponAction;
import weapon.BroadSword;
import weapon.DarkmoonLongbow;
import weapon.YhormsGreatMachete;


public class VendorActionLongbow extends VendorAction {

    /**
     * Constructor for VendorAction that allows player to purchase Darkmoon Longbow from vendor.
     * @param player Player, who is performing the action.
     */
    public VendorActionLongbow(Player player){
        super(player);
    }

    /**
     * Action class for player to purchase increase Longbow from Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when it is purchased
     */
    public String execute(Actor actor, GameMap map, CinderOfAldrich cinder) {
        SwapWeaponAction swap = new SwapWeaponAction(new DarkmoonLongbow(actor));
        swap.execute(actor, map);
        this.player.removeItemFromInventory(cinder);
        return "Paid Cinder of Lord and purchased Darkmoon Longbow";
    }

    /**
     * Shows menuDescription of this class
     * @param actor The actor performing the action.
     * @return String, description of this action to show in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Trade Darkmoon Longbow for Cinder of Lord from the Vendor";
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