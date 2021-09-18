package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class playerDeathAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("hello");
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
}
