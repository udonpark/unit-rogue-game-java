package game.VendorPackage;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.SwapWeaponAction;
import weapon.GiantAxe;

public class VendorActionGA extends VendorAction {
    public VendorActionGA(Player player){
        super(player);
    }

    /**
     * Action class for player to purchase the Giant Axe from Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when it is purchased
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swap = new SwapWeaponAction(new GiantAxe(actor));
        swap.execute(actor, map);
        this.player.subtractSouls(1000);
        return "Paid 1000 Souls and purchased Giant Axe)";
    }

    /**
     * Shows menuDescription of this class
     * @param actor The actor performing the action.
     * @return String, description of this action to show in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Giant Axe for 1000 Souls from the Vendor";
    }

    /**
     * Player is responsible for typing this string to execute this action
     * @return String, i representing hotkey
     */
    @Override
    public String hotkey() {
        return "i";
    }
}
