package game.bonfire;

import edu.monash.fit2099.engine.*;

public class Bonfire extends Ground{

    public Bonfire(){
        super('B');

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }


    @Override
    public String toString() {
        return "Bonfire";
    }
}
