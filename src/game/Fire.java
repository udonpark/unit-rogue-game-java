package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Fire extends Item {
    private int counter;
    /**
     * Constructor for Fire class which extends from Item.
     * It has counter to keep track of turn it was there, make sure that Fire disappears after 4 turns
     */
    public Fire() {
        super("Fire", 'F', false);
        this.counter = 0;
    }

    /**
     * This tick class is Override to increment counter each turn and keep track of it to delete after 4 turns
     * It also finds who is standing in the Yhorm, and if any actor other than Yhorm is there, deals 25 damage
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.counter >= 4) {
            currentLocation.removeItem(this);
        }
        if (currentLocation.containsAnActor() && !(currentLocation.getActor().toString().equals("Yhorm"))){
            currentLocation.getActor().hurt(25);
        this.counter ++;
        }

    }
}
