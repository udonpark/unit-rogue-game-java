package game.bonfire;

import edu.monash.fit2099.engine.*;

public class Bonfire extends Ground{
    /**
     * Constructor for Bonfire
     */
    public Bonfire(){
        super('B');

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
}
