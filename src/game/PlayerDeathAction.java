package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.TokenOfSoulsPackage.TokenOfSouls;

public class PlayerDeathAction extends Action {
    Location lastLocation;
    Player player;
    TokenOfSouls token;

    /**
     * Constructor for the player death action class
     * @param player the actor controlled by the player
     */
    public PlayerDeathAction(Player player){
        this.player =player;

    }

    /**
     * Method that handles the various death events when a player dies.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message to notify the player that they have died/triggered a soft reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //destroys existing tokens
        if( lastLocation != null){
            if (token != null){
                map.at(token.getTokenX(), token.getTokenY()).removeItem(token);
                token = null;
            }
            //creates a new token
            token = new TokenOfSouls("Token", '$',true);
            //case for when player dies to valley
            if (map.locationOf(player).getGround().toString().equals("Valley")){
                map.at(lastLocation.x(), lastLocation.y()).addItem(token);
                token.setTokenX(lastLocation.x());token.setTokenY(lastLocation.y());
            }
            //case for when player dies otherwise
            else {
                map.at(map.locationOf(player).x(),map.locationOf(player).y()).addItem(token);
                token.setTokenX(map.locationOf(player).x()); token.setTokenY(map.locationOf(player).y());

            }
            //currency of player transferred to token
            player.transferSouls(token);
        }
        //move the player back to their last bonfire
        map.moveActor(actor, map.at(player.getLastBonfireX(),player.getLastBonfireY()));
        ResetManager.getInstance().run();
        return "\n" +
                "██╗░░░██╗░█████╗░██╗░░░██╗  ██████╗░██╗███████╗██████╗░  ███╗░░██╗░█████╗░░█████╗░██████╗░\n" +
                "╚██╗░██╔╝██╔══██╗██║░░░██║  ██╔══██╗██║██╔════╝██╔══██╗  ████╗░██║██╔══██╗██╔══██╗██╔══██╗\n" +
                "░╚████╔╝░██║░░██║██║░░░██║  ██║░░██║██║█████╗░░██║░░██║  ██╔██╗██║██║░░██║██║░░██║██████╦╝\n" +
                "░░╚██╔╝░░██║░░██║██║░░░██║  ██║░░██║██║██╔══╝░░██║░░██║  ██║╚████║██║░░██║██║░░██║██╔══██╗\n" +
                "░░░██║░░░╚█████╔╝╚██████╔╝  ██████╔╝██║███████╗██████╔╝  ██║░╚███║╚█████╔╝╚█████╔╝██████╦╝\n" +
                "░░░╚═╝░░░░╚════╝░░╚═════╝░  ╚═════╝░╚═╝╚══════╝╚═════╝░  ╚═╝░░╚══╝░╚════╝░░╚════╝░╚═════╝░";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "null";
    }

    /**
     * sets the last location of the player
     * @param lastLocation previous location of the player.
     */
    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }
}
