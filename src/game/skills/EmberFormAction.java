package game.skills;

import edu.monash.fit2099.engine.*;
import game.ground.Fire;


public class EmberFormAction extends WeaponAction{
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public EmberFormAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location floor;
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();
        floor = new Location(map, x-1, y);
        if (floor.getGround().getDisplayChar() == 'F'){
            floor.setGround(new Fire());
        }

        floor = new Location(map, x, y+1);
        if (floor.getGround().getDisplayChar() == 'F'){
            floor.setGround(new Fire());
        }

        floor = new Location(map, x+1, y);
        if (floor.getGround().getDisplayChar() == 'F'){
            floor.setGround(new Fire());
        }

        floor = new Location(map, x, y-1);
        if (floor.getGround().getDisplayChar() == 'F'){
            floor.setGround(new Fire());
        }
        return "Ember Form activated! YhormTheGiant uses Burn Ground!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Yhorm would burn adjacent floors";
    }

}
