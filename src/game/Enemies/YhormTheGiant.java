package game.Enemies;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.LordOfCinder;
import game.Player;

public class YhormTheGiant extends LordOfCinder {
    private Actor player;

    /**
     * Constructor.
     *
     */
    public YhormTheGiant() {
        super("YhormTheGiant", 'Y', 500);
        this.player = Application.getPlayer();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);



    }
}

