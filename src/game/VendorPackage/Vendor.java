package game.VendorPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;

public class Vendor extends Ground {
    public Vendor() {
        super('F');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    @Override
    public String toString() {
        return "Vendor";
    }

    /**
     * Allows for player to interact with the Vendor
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns actions available to player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

        if (((Player) actor).getSouls() >= 200) {
            actions.add(new VendorActionHP((Player) actor));
        }
        if (((Player) actor).getSouls() >= 500){
            actions.add(new VendorActionBS((Player) actor));
            actions.add(new VendorActionGA((Player) actor));
        }
        return actions;

    }
}
