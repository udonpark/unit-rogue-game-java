package edu.monash.fit2099.engine;

/**
 * A class that provides ability/active skill in WeaponItem
 */
public abstract class WeaponAction extends Action {
    /**
     * Weapon Item
     */
    protected WeaponItem weapon;

    /**
     * Constructor
     * @param weaponItem the weapon item that has capabilities
     */
    public WeaponAction(WeaponItem weaponItem){
        this.weapon = weaponItem;
    }

    @Override
    public abstract String execute(Actor actor, GameMap map);

    @Override
    public abstract String menuDescription(Actor actor);

}
