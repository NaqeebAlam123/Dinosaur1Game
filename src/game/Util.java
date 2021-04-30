package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Util {
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
    public static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
