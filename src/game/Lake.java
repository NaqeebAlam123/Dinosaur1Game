package game;

import edu.monash.fit2099.demo.mars.DemoCapabilities;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class Lake extends Ground {


    private int numberOfSips = 0;
    private int numberOfFish = 5;

    public Lake(){
        super('~');
    }

    public void setNumberOfSips(int numberOfSips) {
        this.numberOfSips = Math.min(numberOfSips,25);
    }
    public int getNumberOfSips(){
        return  numberOfSips;
    }

    public void setNumberOfFish(int numberOfFish){
        this.numberOfFish = Math.min(numberOfFish,25);
    }

    public int getNumberOfFish(){
        return numberOfFish;
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(FlyBehaviour.FLY);
    }
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
