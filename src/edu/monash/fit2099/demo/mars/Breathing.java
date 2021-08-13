package edu.monash.fit2099.demo.mars;

public interface Breathing {
    /**
     * Transfer oxygen to another breathable instance
     * @param breathing an instance/actor that can interact with oxygen.
     */
    void transferOxygen(Breathing breathing);

    /**
     * Increase the oxygen level
     * @param oxygenLevel oxygen level to be added
     * @return transaction condition. False by default because no implementation. True if addition is a success.
     */
    default boolean addOxygen(int oxygenLevel){return false;};
}
