package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * @author Muhammad Naqeeb Alam
 * @see Behaviour
 * @see Actor
 * @see GameMap
 * @see Exit
 * @version 1.0.0
 */
public class Following implements Behaviour {
    /**
     * indicates dinosaur wants to move towards foodSource
     */
    private boolean toFoodSource;
    /**
     * indicates dinosaur wants to move towards other dinosaur
     */
    private boolean toDinosaur;

    /**
     * Intiallises boolean values indicating whether dinsosaur wants to move dinosaur or foodSource
     * @param toFoodSource
     * @param toDinosaur
     */
    public Following( boolean toFoodSource,boolean toDinosaur){
        this.toDinosaur=toDinosaur;
        this.toFoodSource=toFoodSource;
    }

    /**
     * gets action telling where dinosaur has to move
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    public  Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        Location bestDestination = null;
        int bestDistance = map.getXRange().max() + map.getYRange().max();
        ArrayList<Integer> bestDistances = new ArrayList<Integer>();
        ArrayList<Location> possibleDestinations = new ArrayList<>();
        ArrayList<String> possibleDirections = new ArrayList<>();
        ArrayList<String> hotKeys = new ArrayList<>();
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                possibleDestinations.add(destination);
                bestDistances.add(bestDistance);
                hotKeys.add(exit.getName());
            }
        }

        for (int y : map.getYRange()) {
            for (int x : map.getXRange()) {
                if (((toDinosaur) && (map.at(x,y).getActor() instanceof Stegosaur && actor instanceof Stegosaur && ((Stegosaur) actor).getTarget().contentEquals(((Stegosaur)map.at(x,y).getActor()).getGender()))
                ||(map.at(x,y).getActor() instanceof Brachiosaur && actor instanceof  Brachiosaur&& ((Brachiosaur) actor).getTarget().contentEquals(((Brachiosaur)map.at(x,y).getActor()).getGender())))||((toFoodSource) &&(map.at(x,y).getGround() instanceof Tree||map.at(x,y).getGround() instanceof Bush))) {
                        for (int i = 0; i <= possibleDestinations.size() - 1; i++) {
                            int thisDistance = Util.distance(possibleDestinations.get(i), map.at(x, y));
                            if (thisDistance < bestDistances.get(i)) {
                                bestDistances.set(i, thisDistance);
                            }
                        }
                }
            }
        }
        int index = -1;
        for (int i = 0; i <= possibleDestinations.size() - 1; i++) {
            if (bestDistances.get(i) < bestDistance) {
                bestDistance = bestDistances.get(i);
                index = i;
            }
        }
        if (possibleDestinations.size() == 0) {
            return bestDestination.getMoveAction(actor, "around", hotKeys.get(index));
        } else {
            return null;
        }
    }

}
