package game.VendorPackage;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.SwapWeaponAction;
import weapon.GiantAxe;

public class VendorActionGA extends Action {
    private final Player player;
    public VendorActionGA(Player player){
        this.player = player;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swap = new SwapWeaponAction(new GiantAxe(actor));
        swap.execute(actor, map);
        this.player.subtractSouls(1000);
        return "Paid 1000 Souls and purchased Giant Axe)";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy Giant Axe for 1000 Souls from the Vendor";
    }

    @Override
    public String hotkey() {
        return "i";
    }
}
