package weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.Player;
import game.SwapWeaponAction;

import java.util.Random;

/**
 * A class showing BroadSword Weapon
 */
public class BroadSword extends WeaponItem {
    private Actor holder;

    /**
     * Constructor for BroadSword
     * @param holder Actor, this keeps who is currently holding the weapon
     */
    public BroadSword(Actor holder) {
        super("BroadSword", 'B', 30, "hits", 80);
        this.holder = holder;
    }

    /**
     * damage method, a getter for damage. Has 20% to deal double damage
     * @return the damage to be dealt by this weapon
     */
    @Override
    public int damage() {
        if (new Random().nextInt(4) == 0) {
            return super.damage() * 2;
        }
        else{
            return super.damage();
        }
    }

    /**
     * Overrides pickup method
     * @param actor an actor that will interact with this item
     * @return SwapWeaponAction, to swap this weapon with the inventory of a player
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        holder = (Player) actor;
        return new SwapWeaponAction(this);
    }
}
