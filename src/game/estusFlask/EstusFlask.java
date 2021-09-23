package game.estusFlask;

import game.interfaces.Resettable;

public class EstusFlask implements Resettable {
    private int maxCharges;
    private int charges;

    /**
     * Constructor for estus flask
     * @param charges the number of times an estus flask can be used
     * @param maxCharges the maximum number of charges the estus flask can hold
     */
    public EstusFlask(int charges, int maxCharges) {
        this.maxCharges = maxCharges;
        this.charges = charges;
        registerInstance();
    }

    /**
     * Method called when reset manager is run, equates charges to max charges
     */
    @Override
    public void resetInstance() {
        charges = maxCharges;
    }

    /**
     * method to determine estus flask exists in the game
     * @return true if it exists, false otherwise
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * getter for max charges variable
     * @return integer representing the maximum number of charges
     */
    public int getMaxCharges() {
        return maxCharges;
    }

    /**
     * getter for charges variable
     * @return integer representing the number of charges
     */
    public int getCharges() {
        return charges;
    }

    /**
     * Setter for charges
     * @param charges integer to set the number of charges
     */
    public void setCharges(int charges) {
        this.charges = charges;
    }
}
