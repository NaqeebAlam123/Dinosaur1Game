package game.grounds;

import edu.monash.fit2099.engine.*;

public class Portal extends Ground{
    private Location location2;
    private String direction2;
    public Portal(Location location, String direction){
        super('=');
        this.location2 = location;
        this.direction2 = direction;
    }
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions= super.allowableActions(actor, location, direction);
        if (actor instanceof Player){
            actions.add(new MoveActorAction(location2,direction2));
        }
        return actions;
    }
}

