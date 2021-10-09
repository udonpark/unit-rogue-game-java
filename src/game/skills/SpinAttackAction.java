package game.skills;

import edu.monash.fit2099.engine.*;
import game.Enemies.Skeleton;
import game.Enemies.Undead;
import game.Player;
import game.enums.Status;

import java.util.Random;


public class SpinAttackAction extends WeaponAction {
    /**
     * Constructor for Spin Attack of the Giant Axe, and either player or skeleton could trigger this
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Overrides execute method to run this action. It deals 1/2 of the weapon damage to all surrounding
     * enemies. It also registers if the target is dead after the attack.
     *
     * @param actor Actor who is performing this attack
     * @param map GameMap where this attack is taking place
     * @return String message after this action is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int count = 0;

        for (Exit exits: map.locationOf(actor).getExits()) {
            Actor target = map.getActorAt(exits.getDestination());
            if (target != null) {
                target.hurt(this.weapon.damage() / 2);
                System.out.printf("%s hits %s with Spin Attack!%n", actor.toString(), target.toString());
                count++;

                if (!target.isConscious()) {
                    Actions dropActions = new Actions();
                    // drop all items
                    for (Item item : target.getInventory())
                        if (!(target instanceof Player)) {
                            dropActions.add(item.getDropAction(actor));
                        }
                    for (Action drop : dropActions)
                        drop.execute(target, map);

                    if (!(target instanceof Player)){
                        if (target instanceof Undead){
                            ((Player) actor).addSouls(50);
                        }
                        if (target instanceof Skeleton){
                            Random rand = new Random();
                            if (rand.nextInt(2) == 1 && !target.hasCapability(Status.WAS_REVIVED)) {
                                target.heal(100);
                                target.addCapability(Status.WAS_REVIVED);
                                 return System.lineSeparator() + String.format("skeleton was revived");

                            }
                            else{
                                map.removeActor(target);
                                ((Player) actor).addSouls(250);
                            }
                        }
                        map.removeActor(target);
                    }
                }
            }

        }
        return String.format("Spin Attack Used on %d targets!", count);
    }

    /**
     * Description to show to the menu
     * @param actor Actor who is performing this action
     * @return String message to show the menu, telling that the floor is burnt
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " uses Spin Attack";
    }

    /**
     * Hotkey where player has to press if want to trigger this spin attack
     * @return String, s which the game engine detects as an input
     */
    @Override
    public String hotkey() {
        return "s";
    }

    // Old version of my code to get adjacent tiles
//    public ArrayList<Location> getAdjacent(GameMap map, int x, int y){
//        ArrayList<Location> locationList = new ArrayList<Location>();
//        locationList.add(new Location(map, x-1, y-1));
//    }
}
