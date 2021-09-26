package game.VendorPackage;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class VendorActionHP extends VendorAction {

    /**
     * Constructor for VendorAction that allows player to purchase extra HP from vendor.
     * @param player Player, who is performing the action.
     */
    public VendorActionHP(Player player){
        super(player);
    }

    /**
     * Action class for player to purchase increase Maximum HP from Vendor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when it is purchased
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.player.increaseMaxHp(25);
        this.player.subtractSouls(200);
        return String.format("Paid 200 Souls for 25HP. Current HP is %d", this.player.getSouls());
    }

    /**
     * Shows menuDescription of this class
     * @param actor The actor performing the action.
     * @return String, description of this action to show in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Increase Maximum HP for 200 Souls from the Vendor";
    }

    /**
     * Player is responsible for typing this string to execute this action
     * @return String, o representing hotkey
     */
    @Override
    public String hotkey() {
        return "o";
    }
}
