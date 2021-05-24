package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.static_classes.Sky;
import game.dinosaurs.status.FlyBehaviour;

import java.util.Random;

/**
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
     * @param numberOfSips
     */
    public void setNumberOfSips(int numberOfSips) {
        this.numberOfSips = Math.min(numberOfSips,25);
    }

    /**
     * get the number of sips in the lake
     * @return
     */
    public int getNumberOfSips(){
        return  numberOfSips;
    }

    /**
     * sets number of fishes to the lake
     * @param numberOfFish
     */
    public void setNumberOfFish(int numberOfFish){
        this.numberOfFish = Math.min(numberOfFish,25);
    }

    /**
     * gets number of fishes in the lake
     * @return
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
        double rainFall = Math.floor((Math.random() * (1.5))+0.1);
        if (Sky.isRaining()){
            setNumberOfSips(getNumberOfSips()+(int)(rainFall*20));
        }
        if (r.nextInt(100)+1<=60){
            setNumberOfFish(getNumberOfFish()+1);
        }
    }
    }
