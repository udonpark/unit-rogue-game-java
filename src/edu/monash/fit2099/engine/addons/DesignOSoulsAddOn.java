package edu.monash.fit2099.engine.addons;

import game.interfaces.Soul;

/**
 * NOTE: Use this interface to add specific features according to the Assignment's scenario
 */
public interface DesignOSoulsAddOn {
    /**
     * Allows upcasting for Actor, Ground, or Item to a Soul instance if possible.
     *
     * @return a reference to the current Actor/Ground/Item as type Soul,
     *         or null if this Actor/Ground/Item doesn't implement Soul.
     */
    default Soul asSoul(){return this instanceof Soul ? (Soul) this : null;}
}
