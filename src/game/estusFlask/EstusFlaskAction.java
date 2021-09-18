package game.estusFlask;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EstusFlaskAction extends Action {
    private EstusFlask estus;
    private int actorMaxHitPoints;

    public EstusFlaskAction(EstusFlask estus,int maxHitPoints) {
        this.estus = estus;
        this.actorMaxHitPoints = maxHitPoints;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "Actor healed";
        if (estus.getCharges() < 1){

            message = "No Estus charges left";
        }
        else {
            estus.setCharges(estus.getCharges() - 1);
            actor.heal((int) (actorMaxHitPoints * 0.4));

        }
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Unkindled drinks Estus Flask (%d/%d)",estus.getCharges(),estus.getMaxCharges());
    }

    public void setActorMaxHitPoints(int actorMaxHitPoints) {
        this.actorMaxHitPoints = actorMaxHitPoints;
    }

    @Override
    public String hotkey() {
        return "a";
    }
}
