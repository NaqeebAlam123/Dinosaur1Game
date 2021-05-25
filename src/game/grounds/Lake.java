package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.static_classes.Sky;
import game.dinosaurs.status.FlyBehaviour;

import java.util.Random;

/**
 * This class allows lake to be set on the ground
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Ground
 */
public class Lake extends Ground {

    /**
     * number of sips in lake
     */
    private int numberOfSips = 0;
    /**
     * number of fishes in lake
     */
    private int numberOfFish = 5;

    /**
     * initialises a display character for lake
     */
    public Lake(){
        super('~');
    }

    /**
     * set number of sips in the lake
     * @param numberOfSips number of sips
     */
    public void setNumberOfSips(int numberOfSips) {
        this.numberOfSips = Math.min(numberOfSips,25);
    }

    /**
     * get the number of sips in the lake
     * @return number of sips
     */
    public int getNumberOfSips(){
        return  numberOfSips;
    }

    /**
     * sets number of fishes to the lake
     * @param numberOfFish number of fish
     */
    public void setNumberOfFish(int numberOfFish){
        this.numberOfFish = Math.min(numberOfFish,25);
    }

    /**
     * gets number of fishes in the lake
     * @return number of fish
     */
    public int getNumberOfFish(){
        return numberOfFish;
    }
    /**
     * returns true if the actor is a flying dinosaur
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(FlyBehaviour.FLY);
    }

    /**
     * adds number of fishes and sips into the lake depending on the conditions
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Random r = new Random();
        double rainFall = Math.floor((Math.random() * (1.5))+0.1);//produces a random value between 0.1 and 0.6
        if (Sky.isRaining()){ // if it is raining, then use the rainfall local variable to calculate the number of sips to be added
            setNumberOfSips(getNumberOfSips()+(int)(rainFall*20));
        }
        if (r.nextInt(100)+1<=60){//for a 60% chance,add an extra fish to the lake
            setNumberOfFish(getNumberOfFish()+1);
        }
    }
    }
