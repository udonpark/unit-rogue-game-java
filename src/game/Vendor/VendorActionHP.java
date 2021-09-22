package game.Vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class VendorActionHP extends Action {
    private final Player player;
    public VendorActionHP(Player player){
        this.player = player;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        this.player.increaseMaxHp(25);
        this.player.subtractSouls(200);
        return String.format("Paid 200 Souls for 25HP. Current HP is %d", this.player.getSouls());
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Increase Maximum HP for 200 Souls from the Vendor";
    }

    @Override
    public String hotkey() {
        return "V1";
    }
}
