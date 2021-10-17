package game.Enemies;

import edu.monash.fit2099.engine.Actor;

public abstract class Enemies extends Actor {
    private boolean inRange = false;
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    /**
     * Setter for the range boolean attribute
     * @param inRange true or false
     */
    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }

    public boolean isInRange() {
        return inRange;
    }
}
