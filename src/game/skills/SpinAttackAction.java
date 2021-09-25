package game.skills;

import edu.monash.fit2099.engine.*;


public class SpinAttackAction extends WeaponAction {
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        int count = 0;

        for (Exit exits: map.locationOf(actor).getExits()) {
            Actor target = map.getActorAt(exits.getDestination());
            if (target != null){
                target.hurt(this.weapon.damage() / 2);
                System.out.printf("%s hits %s with Spin Attack!%n", actor.toString(), target.toString());
                count++;
            }
        }
        return String.format("Spin Attack Used on %d targets!", count);

//
//        target = map.getActorAt(new Location(map, x, y+1));
//        if (target != null){
//            target.hurt(this.weapon.damage() / 2);
//            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
//            count += 1;
//        }
//
//        target = map.getActorAt(new Location(map, x+1, y));
//        if (target != null){
//            target.hurt(this.weapon.damage() / 2);
//            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
//            count += 1;
//        }
//
//        target = map.getActorAt(new Location(map, x, y-1));
//        if (target != null){
//            target.hurt(this.weapon.damage() / 2);
//            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
//            count += 1;
//        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " uses Spin Attack";
    }


    @Override
    public String hotkey() {
        return "Spin";
    }

//    public ArrayList<Location> getAdjacent(GameMap map, int x, int y){
//        ArrayList<Location> locationList = new ArrayList<Location>();
//        locationList.add(new Location(map, x-1, y-1));
//    }
}
