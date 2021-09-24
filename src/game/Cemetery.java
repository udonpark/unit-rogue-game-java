package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.Undead;

import java.util.Random;

public class Cemetery extends Ground {
    private GameMap map;

    public Cemetery() {
        super('c');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public String toString() {
        return "Cemetery";
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Random rand = new Random();
        if (rand.nextInt(3) == 1) {
            try{
                location.addActor(new Undead("Undead"));
            }catch (Exception e){;}
        }
    }
}
