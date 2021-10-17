package game.skills;

import edu.monash.fit2099.engine.*;
import game.Fire;


public class EmberFormAction extends WeaponAction{
    /**
     * Constructor for EmberFormAction, which is triggered by YhormsGreatMachete, and this inherits
     * from WeaponAction
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public EmberFormAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Only Yhorm AND PLAYER can hold Machete, and hence able to execute this WeaponAction.
     * This turns adjacent dirt into fire for 4 turns which deals 25 to non-Yhorm.
     * This is done by adding item to the ground.
     *
     * @see Fire for how we implemented this.
     * @param actor Actor to execute this action
     * @param map GameMap where this actor lies
     * @return String message to print after this action is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exits: map.locationOf(actor).getExits()) {
            Ground floor = exits.getDestination().getGround();
            if (floor.getDisplayChar() == '.'){
                exits.getDestination().addItem(new Fire());
            }

            // This comment is my old version of not using Exits.
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
        return "Ember Form activated! Burn Ground ability is used!";
    }

    /**
     * Description to show to the menu
     * @param actor Actor who is performing this action
     * @return String message to show the menu, telling that the floor is burnt
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use Machete to burn adjacent floors";
    }

}
