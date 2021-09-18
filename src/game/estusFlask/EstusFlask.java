package game.estusFlask;

import game.interfaces.Resettable;

public class EstusFlask implements Resettable {
    private int maxCharges;
    private int charges;

    public EstusFlask(int charges, int maxCharges) {
        this.maxCharges = maxCharges;
        this.charges = charges;
        registerInstance();
    }

    @Override
    public void resetInstance() {
        charges = maxCharges;
    }

    @Override
    public boolean isExist() {
        return true;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}
