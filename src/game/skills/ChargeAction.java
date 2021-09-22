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
        String message = String.format("Charging StormRuler, %d/3", this.weapon.getCharge());
        if (this.weapon.getCharge() == 3){
            message += "/nCharging complete, Wind Slash available";
            actor.removeCapability(Status.ATTACK_LIMITED);
        }
        return message;
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
