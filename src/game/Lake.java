package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * This is a Lake that allows dinosaurs to take sips and there are fishes inside
 */
public class Lake extends Ground {


    private int numberOfSips = 0;
    private int numberOfFish = 5;

    /**
     * constructor for lake
     */
    public Lake(){
        super('~');
    }

    /**
     * Sets the number of Sips remaining for the lake
     * @param numberOfSips
     */
    public void setNumberOfSips(int numberOfSips) {
        this.numberOfSips = Math.min(numberOfSips,25);
    }

    /**
     * A method that returns the number of sips remaining
     * @return number of sips
     */
    public int getNumberOfSips(){
        return  numberOfSips;
    }

    /**
     * Sets the number of fish in the lake
     * @param numberOfFish
     */
    public void setNumberOfFish(int numberOfFish){
        this.numberOfFish = Math.min(numberOfFish,25);
    }

    /**
     * Method to return the number of fish in the lake
     * @return number of fish
     */
    public int getNumberOfFish(){
        return numberOfFish;
    }

    /**
     * A method that check if the actor can enter the lake
     * @param actor the Actor to check
     * @return true/false on the actor's ability to enter the lake
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(FlyBehaviour.FLY);
    }

    /**
     * Allows the lake to experience the flow of time
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Random r = new Random();
        double rainFall = Math.floor((Math.random() * (1.5))+0.1);
        if (Sky.isRaining()){
            setNumberOfSips(getNumberOfSips()+(int)(rainFall*20));
        }
        if (r.nextInt(100)+1<=60){
            setNumberOfFish(getNumberOfFish()+1);
        }
    }
    }
