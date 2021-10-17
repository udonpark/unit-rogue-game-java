package game.Chest;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Application;
import game.Enemies.Mimic;
import game.TokenOfSoulsPackage.TokenOfSouls;

import java.util.Random;

public class ChestAction extends Action {

    TokenOfSouls token;
    Chest chest;
    Location location;

    public ChestAction(Chest chest) {
        this.chest = chest;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        location = chest.getLocation();
        Random rand = new Random();
        map.removeActor(chest);
        int randInt = rand.nextInt(2);
        if(randInt == 0){
            map.addActor(new Mimic("Mimic"),map.at(location.x(), location.y()));
            return "Mimic spawned";
        }
        else{
            int randomInt = rand.nextInt(3);
            int i = 0;
            while(i <= randomInt){
                token = new TokenOfSouls("Token", '$',true);
                token.addSouls(100);
                map.at(location.x(), location.y()).addItem(token);
                i += 1;
            }
            return "Tokens were dropped";
        }


    }

    @Override
    public String menuDescription(Actor actor) {
        return "Open the Chest";
    }

    @Override
    public String hotkey() {
        return "o";
    }
}
