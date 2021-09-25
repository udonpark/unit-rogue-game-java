package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class YhormTheGiant extends LordOfCinder{

    /**
     * Constructor.
     *
     */
    public YhormTheGiant() {
        super("YhormTheGiant", 'Y', 500);
    }

    @Override
    public String toString() {
        return "Yhorm";
    }

    //    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display){
//        ;
//    }
}

