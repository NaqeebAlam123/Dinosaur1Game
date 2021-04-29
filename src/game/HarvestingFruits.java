package game;

import edu.monash.fit2099.engine.*;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Action,Fruit
 */
public class HarvestingFruits extends Action {
    /**
     * Fruit to be harvested
     */
    private Fruit fruit;

    /**
     * Initialises Fruit object
     * @param fruit
     */
    public HarvestingFruits(Fruit fruit){
        this.fruit=fruit;

    }

    /**
     * This method removes the fruit from bush or Tree and adds to player inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location=map.locationOf(actor);
        if(location.getGround() instanceof  Tree){
            Tree tree=(Tree) location.getGround();
            tree.remove(fruit);
        }
        else if(location.getGround() instanceof Bush){
            Bush bush=(Bush) location.getGround();
            bush.remove(fruit);

        }
        Player player=(Player)actor;
        player.incrementPoints(10);
        actor.addItemToInventory(fruit);
        return menuDescription(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor+" picks up the "+fruit);
    }
}
