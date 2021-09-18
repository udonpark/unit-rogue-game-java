package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.TokenOfSouls.TokenOfSouls;

public class PlayerDeathAction extends Action {
    Location lastLocation;
    Player player;
    TokenOfSouls token;

    public PlayerDeathAction(Player player){
        this.player =player;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if( lastLocation != null){
            if (token != null){
                map.at(token.getTokenX(), token.getTokenY()).removeItem(token);
                token = null;
            }

            token = new TokenOfSouls("Token", '$',true);
            if (map.locationOf(player).getGround().toString().equals("Valley")){
                map.at(lastLocation.x(), lastLocation.y()).addItem(token);
                token.setTokenX(lastLocation.x());token.setTokenY(lastLocation.y());
            }
            else {
                map.at(map.locationOf(player).x(),map.locationOf(player).y()).addItem(token);
                token.setTokenX(map.locationOf(player).x()); token.setTokenY(map.locationOf(player).y());

            }
            player.transferSouls(token);
        }

        map.moveActor(actor, map.at(38,12));
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

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }
}
