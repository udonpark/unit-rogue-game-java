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
     * Constructor for Charge action, which charges one of the 3 every turn
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(StormRuler weaponItem) {
        super(weaponItem);
        this.weapon = weaponItem;
    }

    /**
     * Only Player can hold StormRuler, and hence execute this WeaponAction. This limites the movement of
     * the player while charging. It also removes that status once charging is complete
     * @param actor Actor to execute this action
     * @param map GameMap where this actor lies
     * @return String message to print after this action is done
     */
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

    /**
     * Description to show
     * @param actor Actor who is performing this action
     * @return String message to show the menu, including current charge of the StormRuler
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Charge StormRuler, currently %d/3", this.weapon.getCharge());
    }

    /**
     * Hotkey used when player wants to perform this action
     * @return String, c to perfomr this WeaponAction
     */
    @Override
    public String hotkey() {
        return "c";
    }
}
