package game.skills;

import edu.monash.fit2099.engine.*;
import game.Fire;


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
        for (Exit exits: map.locationOf(actor).getExits()) {
            Ground floor = exits.getDestination().getGround();
            if (floor.getDisplayChar() == '.'){
                exits.getDestination().addItem(new Fire());
            }

//        floor = new Location(map, x, y+1);
//        if (floor.getGround().getDisplayChar() == '.'){
//            floor.addItem(new Fire());
//        }
//
//        floor = new Location(map, x+1, y);
//        if (floor.getGround().getDisplayChar() == '.'){
//            floor.addItem(new Fire());
//        }
//
//        floor = new Location(map, x, y-1);
//        if (floor.getGround().getDisplayChar() == '.'){
//            floor.addItem(new Fire());
        }
        return "Ember Form activated! Yhorm The Giant uses Burn Ground!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Yhorm would burn adjacent floors";
    }

}
