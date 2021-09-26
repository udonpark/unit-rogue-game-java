package game.Terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.Enemies.Undead;

import java.util.Random;

/**
 *
 */
public class Cemetery extends Ground {
    private GameMap map;
    private Player player;

    /**
     * Constructor for cemetery, displayed a 'c' on map
     */
    public Cemetery() {
        super('c');
    }

    /**
     * implements impassable terrain, or terrain that is only passable if conditions are met.
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * returns a string representing the Cemetery class
     * @return string representing Cemetery class
     */
    @Override
    public String toString() {
        return "Cemetery";
    }

    /**
     * Method is called every turn, has a 25% chance to spawn an undead
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Random rand = new Random();
        if (rand.nextInt(4) == 1) {
            try{
                location.addActor(new Undead("Undead"));
            }catch (Exception e){;}
        }



    }
}
