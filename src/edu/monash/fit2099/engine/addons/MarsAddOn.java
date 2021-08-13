package edu.monash.fit2099.engine.addons;

import edu.monash.fit2099.demo.mars.Breathing;

public interface MarsAddOn {
    /**
     * Allows upcasting for Actor to a Breathable instance if possible.
     *
     * @return a reference to the current Actor that breathes,
     *         or null if this Actor doesn't implement it.
     */
    default Breathing asMartian(){return this instanceof Breathing ? (Breathing) this : null;}
}
