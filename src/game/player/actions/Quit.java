package game.player.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This class provides computation in regards to Challenge game mode
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see game.player.Player
 * @see Action
 * @see GameMap
 */
public class Quit extends Action {
    /**
     * upon the execution of this method player will be removed from map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * The statement inside the method is printed on the UI asking user to choose the option
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return ("Do you want to quit this game?");
    }
}
