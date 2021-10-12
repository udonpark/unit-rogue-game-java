package game.bonfire;

import edu.monash.fit2099.engine.*;
import game.Application;

import java.util.ArrayList;

public class Bonfire extends Ground{
    /**
     * Constructor for Bonfire
     */
    private String name;
    private boolean lit = false;
    private boolean current = false;
    private Location location;
    private static ArrayList<Bonfire> bonfires = new ArrayList<>();
    private static int unnamedBonfireCount;

    public Bonfire(){
        super('B');

    }

    @Override
    public void tick(Location location) {
        if (name == null){
            name = "Unnamed Bonfire " + unnamedBonfireCount;
            unnamedBonfireCount++;
        }
        super.tick(location);
        this.location = location;

    }

    /**
     * Method that allows / disallows actors to walk atop the ground
     * @param actor the Actor to check
     * @return true if actor allowed to walk on ground, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * Returns a string associated with ground class
     * @return string that represents the class.
     */
    @Override
    public String toString() {
        return "Bonfire";
    }

    /**
     * Method to give player the rest action
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns actions available to player
     */

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.map() == Application.getProfaneCapital() && location.x() == 38 && location.y() == 11){
            name = "Firelink Shrine Bonfire";
        }
        else if (location.map() == Application.getAnorLondoMap() && location.x() == 76 && location.y() == 3){
            name = "Anor Londo Bonfire";
        }
        Actions actions = super.allowableActions(actor, location, direction);
        if (this.lit){
            actions.add(new BonfireAction(actor,name));
            for (Bonfire bonfire : bonfires){
                if (bonfire.name != name && bonfire.isLit()){
                    actions.add(new BonfireTravelAction(bonfire));
                }
            }
        }else{actions.add(new BonfireLightAction(actor,this,name));}
        return actions;
    }

    public void toggleLit(){
        lit = true;

    }
    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public boolean isLit() {
        return lit;
    }

    public static ArrayList<Bonfire> getBonfires() {
        return bonfires;
    }

    public void setName(String name) {
        this.name = name;
    }
}
