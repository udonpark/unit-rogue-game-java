package game.skills;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;
import weapon.StormRuler;

public class ChargeAction extends WeaponAction {
    private StormRuler weapon;
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(StormRuler weaponItem) {
        super(weaponItem);
        this.weapon = weaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.ATTACK_LIMITED);
        this.weapon.increaseCharge();
        return String.format("Charging StormRuler, %d/3", this.weapon.getCharge());
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Charge StormRuler, currently %d/3", this.weapon.getCharge());
    }

    @Override
    public String hotkey() {
        return "c";
    }
}
