package game.grounds;

import edu.monash.fit2099.engine.*;
import game.player.Player;

/**
 * This is a class that teleports the user to and from a different map
 */
public class Portal extends Ground{
    private Location location2;
    private String direction2;

    /**
     * Constructor for portal
     * @param location Location of the next area
     * @param direction Description of the direction
     */
    public Portal(Location location, String direction){
        super('=');
        this.location2 = location;
        this.direction2 = direction;
    }

    /**
     * Returns the actions that can be done on Vending Machine
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of action
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions= super.allowableActions(actor, location, direction);
        if (actor instanceof Player){
            actions.add(new MoveActorAction(location2,direction2)); //sends the player to the location
        }
        return actions;
    }
}

