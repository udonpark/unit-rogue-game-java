package game.skills;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import weapon.StormRuler;

public class WindSlashAction extends WeaponAction {
    private final StormRuler weapon;
    /**
     * Constructor for Wind Slash, which is only available for StormRuler when Player has charged
     * 3 charges.
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public WindSlashAction(StormRuler weaponItem) {
        super(weaponItem);
        this.weapon = weaponItem;
    }

    /**
     * Overrides execute method to run this action. It deals 2 times the weapon damage of the Storm Ruler
     * to Yhorm which is in adjacent squares, stuns the Yhorm for one turn and resets charge to 0.
     * If Yhorm is not found in adjacent, it returns error message andcharge is kept.
     *
     * @param actor Actor who is performing this attack, usually player
     * @param map GameMap where this attack is taking place
     * @return String message after this action is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exits: map.locationOf(actor).getExits()){
            Actor target = exits.getDestination().getActor();
            if (target != null) {
                if (target.toString().equals("Yhorm The Giant")) {
                    target.hurt(this.weapon.damage() * 2);
                    target.addCapability(Status.STUNNED);
                    this.weapon.resetCharge();
                    return actor.toString() + " attacks and stuns " + target.toString();
                }
            }
        }
        return "Wind Slash missed! Target has to be in adjacent squares.";
    }

    // Old version of code where I was not aware of the Exit class.
//        Actor target = map.getActorAt(new Location(map, x-1, y));
//        if (target.toString().equals("Yhorm The Giant")){
//            target.hurt(this.weapon.damage() / 2);
//            target.addCapability(Status.STUNNED);
//            this.weapon.resetCharge();
//            return "Unkindled attacks and stuns YhormTheGiant!";
//        }

//        target = map.getActorAt(new Location(map, x, y+1));
//        if (target.toString().equals("YhormTheGiant")){
//            target.hurt(this.weapon.damage() / 2);
//            target.addCapability(Status.STUNNED);
//            this.weapon.resetCharge();
//            return "Unkindled attacks and stuns YhormTheGiant!";
//        }
//
//        target = map.getActorAt(new Location(map, x+1, y));
//        if (target.toString().equals("YhormTheGiant")){
//            target.hurt(this.weapon.damage() / 2);
//            target.addCapability(Status.STUNNED);
//            this.weapon.resetCharge();
//            return "Unkindled attacks and stuns YhormTheGiant!";
//        }
//
//        target = map.getActorAt(new Location(map, x, y-1));
//        if (target.toString().equals("YhormTheGiant")){
//            target.hurt(this.weapon.damage() / 2);
//            target.addCapability(Status.STUNNED);
//            this.weapon.resetCharge();
//            return "Unkindled attacks and stuns YhormTheGiant!";
//        }

    /**
     * Description to show to the menu
     * @param actor Actor who is performing this action
     * @return String message to show the menu, telling that the floor is burnt
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use Wind Slash on Yhorm(Only adjacent)";
    }

    /**
     * Hotkey where player has to press if want to trigger this spin attack
     * @return String, w which the game engine detects as an input
     */
    @Override
    public String hotkey() {
        return "w";
    }
}
