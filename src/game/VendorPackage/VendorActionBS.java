package game.VendorPackage;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.SwapWeaponAction;
import weapon.BroadSword;


public class VendorActionBS extends Action {
    private final Player player;
    public VendorActionBS(Player player){
        this.player = player;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swap = new SwapWeaponAction(new BroadSword(actor));
        swap.execute(actor, map);
        this.player.subtractSouls(500);
        return "Paid 500 Souls and purchased BroadSword";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy BroadSword for 500 Souls from the Vendor";
    }

    @Override
    public String hotkey() {
        return "u";
    }
}