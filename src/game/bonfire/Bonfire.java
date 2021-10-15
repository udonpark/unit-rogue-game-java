package game.bonfire;

import edu.monash.fit2099.engine.*;
import game.Application;

import java.util.ArrayList;

public class Bonfire extends Ground{

    private String name;
    private boolean lit = false;
    private Location location;
    private static ArrayList<Bonfire> bonfires = new ArrayList<>();
    private static int bonfireCount;
    private ArrayList<String> bonfireNames;

    /**
     * Constructor for Bonfire
     */
    public Bonfire(){
        super('B');
    }

    /**
     * Tick called every turn, function updates the location of bonfire and names them according to the order they are generated
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        bonfireNames = Application.getBonfireNames();
        if (name == null){

            if (bonfireCount < bonfireNames.size()){
                name = bonfireNames.get(bonfireCount);
                bonfireCount++;
            }else{name = "Bonfire #"+bonfireCount+" - Unnamed Bonfire";}

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
     * Method to give player access to interactions with the bonfire
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns actions available to player
     */

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (bonfireCount == 0){
            bonfireNames = Application.getBonfireNames();
            name = bonfireNames.get(bonfireCount);
            bonfireCount++;
        }
        Actions actions = super.allowableActions(actor, location, direction);
        if (this.lit){
            actions.add(new BonfireAction(actor,name));
            for (Bonfire bonfire : bonfires){
                if (!bonfire.name.equals(name)  && bonfire.isLit()){
                    actions.add(new BonfireTravelAction(bonfire));
                }
            }
        }else{actions.add(new BonfireLightAction(actor,this));}
        return actions;
    }

    /**
     * Method that sets the lit instance variable to true
     */
    public void lightBonfire(){
        lit = true;

    }

    /**
     * Getter for the location instance variable
     * @return Location of the bonfire
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for the name of the bonfire class
     * @return String representing bonfire's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that checks if the bonfire variable lit is set to true or not
     * @return true if the bonfire variable lit is true, false otherwise
     */
    public boolean isLit() {
        return lit;
    }

    /**
     * Getter for the array list of bonfires
     * @return arraylist of bonfires
     */
    public static ArrayList<Bonfire> getBonfires() {
        return bonfires;
    }
}
