package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Fire extends Item {
    int counter;
    /**
     * Constructor.
     *
     */

    public Fire() {
        super("Fire", 'F', false);
        counter = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        counter ++;
        if (counter == 4) {
            currentLocation.removeItem(this);
        }
    }
}
