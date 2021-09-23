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
     * Constructor of token of souls
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public TokenOfSouls(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        resetInstanceSouls();
    }
    /**
     * Method used to set a variable within a Soul class that represents its souls to 0
     */
    @Override
    public void resetInstanceSouls() {
        currentSouls = 0;
    }
    /**
     * Transfers all souls of the caller to the soul object specified in the parameter
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(currentSouls);
        this.subtractSouls(currentSouls);
    }
    /**
     * Method to increment the number of souls in a soul object
     * @param souls number of souls to be incremented.
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addSouls(int souls) {
        if (souls > 0){
            currentSouls += souls;
            return true;
        }
        return false;

    }

    /**
     * getter for token x coordinate
     * @return integer representing x of token
     */
    public int getTokenX() {
        return tokenX;
    }

    /**
     * sets the x location of token
     * @param tokenX x location to place token
     */
    public void setTokenX(int tokenX) {
        this.tokenX = tokenX;
    }

    /**
     * getter for token y coordinate
     * @return integer representing y of token
     */
    public int getTokenY() {
        return tokenY;
    }
    /**
     * sets the y location of token
     * @param tokenY y location to place token
     */
    public void setTokenY(int tokenY) {
        this.tokenY = tokenY;
    }

    /**
     * Method to handle actor picking up token
     * @param actor an actor that will interact with this item
     * @return returns PickUpItemAction object
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpTokenAction(this);
    }
}
