package game.bonfire;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class BonfireLightAction extends Action {
    private String name;
    private Bonfire bonfire;
    private Actor actor;
    public BonfireLightAction(Actor actor, Bonfire bonfire ,String name) {
        this.name = name;
        this.actor = actor;
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Bonfire.getBonfires().add(bonfire);
        ((Player) actor).setLastBonfireX(map.locationOf(actor).x()); ((Player) actor).setLastBonfireY(map.locationOf(actor).y());
        ((Player) actor).setLastMap(map);
        bonfire.toggleLit();
        return name + "lit";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Light " + name;
    }
}
