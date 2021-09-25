package game.skills;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import weapon.StormRuler;

public class WindSlashAction extends WeaponAction {
    private final StormRuler weapon;
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public WindSlashAction(StormRuler weaponItem) {
        super(weaponItem);
        this.weapon = weaponItem;
    }

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

        return "Wind Slash missed! Target has to be in adjacent squares.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Use Wind Slash on Yhorm(Only adjacent)";
    }

    @Override
    public String hotkey() {
        return "w";
    }
}
