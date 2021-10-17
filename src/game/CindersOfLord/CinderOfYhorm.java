package game.CindersOfLord;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;

public class CinderOfYhorm extends CindersOfLord{

    /***
     * Constructor for Cinder of Lord for Yhorm
     */
    public CinderOfYhorm() {
        super("CinderOfLord(Yhorm)", 'C');
    }

//    /**
//     * Player picks up cinder
//     * @param actor an actor that will interact with this item
//     * @return action to pick up
//     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }
}
