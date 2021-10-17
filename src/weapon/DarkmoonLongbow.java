package weapon;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.AttackAction;
import game.Chest.Chest;
import game.Enemies.Enemies;
import game.Player;
import game.enums.Status;
import game.skills.EmberFormAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DarkmoonLongbow extends WeaponItem {
    private Actor holder;

    /**
     * Constructor for Darkmoon Longbow
     * @param holder the actor who is holding Longbow, both player and Aldrich could hold this weapon
     */
    public DarkmoonLongbow(Actor holder){
        super("Darkmoon Longbow", 'D', 70, "hits", 80);
        this.holder = holder;
        this.portable = false;
    }

    /**
     * Returns all allowable actions, which is EmberFormActionAD if the status is in RAGE_MODE.
     * @return List of possible actions by Longbow
     */
    @Override
    public List<Action> getAllowableActions(){
        Actions actions = new Actions();
        GameMap map;
        if (Application.getProfaneCapital().contains(holder)){
            map = Application.getProfaneCapital();
        }else{map = Application.getAnorLondoMap();}
        Location here = map.locationOf(holder);
        Location there;
        ArrayList<Actor> actorsInRange = new ArrayList<>();
        for (Exit exit: here.getExits()){

            for (int i = -2; i<3;i++){
                for (int j = -2; j < 3; j++){
                    there = new Location(exit.getDestination().map(), exit.getDestination().x()+i, exit.getDestination().y()+j);
                    if (there.getActor() instanceof Chest) {
                        continue;

                    }
                    if (there.getActor() instanceof Player && there.getActor() != holder) {
                        actions.add(new AttackAction(there.getActor(),""));

                    }
                    else if (there.containsAnActor() && there.getActor() != holder && !((Enemies)there.getActor()).isInRange()) {
                        ((Enemies) there.getActor()).setInRange(true);
                        actorsInRange.add(there.getActor());
                        String direction = "";
                        if (here.y()< there.y()){
                            direction += " South ";
                        }
                        if (here.y() > there.y()) {
                            direction += " North ";
                        }
                        if(here.x() > there.x()){
                            direction += " West ";
                        }
                        if(here.x() < there.x()){
                            direction += " East ";
                        }
                        actions.add(new AttackAction(there.getActor(), direction));
                    }
                }
            }
        }
        // Since implementation of Ember Form are optional, there is no Allowable Actions here
//        if (this.holder.hasCapability(Status.RAGE_MODE)){
//            actions.add(new EmberFormAction(this));
//        }
        for (Actor actors: actorsInRange ){
            ((Enemies) actors).setInRange(false);
        }
        return actions.getUnmodifiableActionList();
    }

    /**
     * damage method, a getter for damage. Has 15% to deal double damage
     * @return the damage to be dealt by this weapon
     */
    @Override
    public int damage() {
        if (new Random().nextInt(4) == 0) {
            return super.damage() * 2;
        }
        else{
            return super.damage();
        }
    }

}
