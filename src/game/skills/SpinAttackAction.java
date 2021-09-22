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
        int x, y;
        x = map.locationOf(actor).x();
        y = map.locationOf(actor).y();
        Actor target = map.getActorAt(new Location(map, x-1, y));
        if (target != null){
            target.hurt(this.weapon.damage() / 2);
            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
            count += 1;
        }

        target = map.getActorAt(new Location(map, x, y+1));
        if (target != null){
            target.hurt(this.weapon.damage() / 2);
            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
            count += 1;
        }

        target = map.getActorAt(new Location(map, x+1, y));
        if (target != null){
            target.hurt(this.weapon.damage() / 2);
            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
            count += 1;
        }

        target = map.getActorAt(new Location(map, x, y-1));
        if (target != null){
            target.hurt(this.weapon.damage() / 2);
            System.out.printf("Unkindled hits %c%n with Spin Attack!", target.getDisplayChar());
            count += 1;
        }

        return String.format("Spin Attack Used on %d targets!", count);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled uses Spin Attack";
    }


    @Override
    public String hotkey() {
        return "s";
    }

//    public ArrayList<Location> getAdjacent(GameMap map, int x, int y){
//        ArrayList<Location> locationList = new ArrayList<Location>();
//        locationList.add(new Location(map, x-1, y-1));
//    }
}
