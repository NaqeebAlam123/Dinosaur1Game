package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * a file with static functions
 * @author Muhammad Naqeeb Alam
 * @see Following
 * @see Egg
 * @see Tree
 * @version 1.0.0
 */
public class Util {
    /**
     * This method finds player on the map and return player object given the GameMap object
     * @param map
     * @return
     */
    public static Player findPlayer(GameMap map) {
        for (int y : map.getYRange()) {
            for (int x : map.getXRange()) {
                Location location = map.at(x, y);
                if (location.containsAnActor()) {
                    Actor actor = location.getActor();
                    if (actor instanceof Player) {
                        return (Player) actor;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method calculates the distance between the two location objects given as parameter
     * @param a
     * @param b
     * @return
     */
    public static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
