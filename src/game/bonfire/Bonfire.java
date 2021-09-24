package game.bonfire;

import edu.monash.fit2099.engine.*;

public class Bonfire extends Ground{
    /**
     * Constructor for Bonfire
     */

    private BonfireAction bonfireAction;
    public Bonfire(){
        super('B');
    }

    @Override
    public void tick(Location location) {

        super.tick(location);
    }

    /**
     * Method that allows / disallows actors to walk atop the ground
     * @param actor the Actor to check
     * @return true if actor allowed to walk on ground, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * Returns a string associated with ground class
     * @return string that represents the class.
     */
    @Override
    public String toString() {
        return "Bonfire";
    }

    /**
     * Method to give player the rest action
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns actions available to player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        bonfireAction = new BonfireAction(actor);
        actions.add(bonfireAction);
        return actions;
    }
}
