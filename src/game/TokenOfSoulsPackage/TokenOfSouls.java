package game.TokenOfSoulsPackage;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.interfaces.Soul;

public class TokenOfSouls extends Item implements Soul {

    private int tokenX;
    private int tokenY;
    private int currentSouls;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public TokenOfSouls(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        initializeInstanceSouls();
    }

    @Override
    public void initializeInstanceSouls() {
        currentSouls = 0;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(currentSouls);
        this.subtractSouls(currentSouls);
    }

    @Override
    public boolean addSouls(int souls) {
        if (souls > 0){
            currentSouls += souls;
            return true;
        }
        return false;

    }

    public int getTokenX() {
        return tokenX;
    }

    public void setTokenX(int tokenX) {
        this.tokenX = tokenX;
    }

    public int getTokenY() {
        return tokenY;
    }

    public void setTokenY(int tokenY) {
        this.tokenY = tokenY;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpTokenAction(this);
    }
}
