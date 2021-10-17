package game.VendorPackage;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.enums.Status;

public class Vendor extends Ground {
    /**
     * Constructor for Vendor on ground, which is represented by F
     */
    public Vendor() {
        super('F');
    }

    /**
     * Gives permission to actor to enter in this tile
     * @param actor the Actor to check
     * @return boolean whether or not actor can enter Vendor
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * returns a string representing the Vendor class
     * @return string called Vendor
     */
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
        }
        if (((Player) actor).getSouls() >= 1000) {
            actions.add(new VendorActionGA((Player) actor));
        }
        if (((Player) actor).hasCapability(Status.KILLED_YHORM)){
            actor.removeCapability(Status.KILLED_YHORM);
            actions.add(new VendorActionMachete((Player) actor));
        }
        if (((Player) actor).hasCapability(Status.KILLED_ALDRICH)){
            actor.removeCapability(Status.KILLED_ALDRICH);
            actions.add(new VendorActionMachete((Player) actor));
        }
        return actions;
    }
}

