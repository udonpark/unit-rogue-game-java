package game.VendorPackage;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.SwapWeaponAction;
import weapon.BroadSword;


public class VendorActionBS extends Action {
    private final Player player;

    /**
     * Constructor for VendorAction to buy BroadSword, which inherits from Action
     * @param player takes in a Player argument who makes the arction
     */
    public VendorActionBS(Player player){
        this.player = player;
    }

    /**
     * Method to execute this action. This BS stands for BroadSword
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message to print to menu when purchased.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swap = new SwapWeaponAction(new BroadSword(actor));
        swap.execute(actor, map);
        this.player.subtractSouls(500);
        return "Paid 500 Souls and purchased BroadSword";
    }

    /**
     * Shows menuDescription of this class
     * @param actor The actor performing the action.
     * @return String, description of this action to show in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy BroadSword for 500 Souls from the Vendor";
    }

    /**
     * Player is responsible for typing this string to execute this action
     * @return String, u representing hotkey
     */
    @Override
    public String hotkey() {
        return "u";
    }
}
