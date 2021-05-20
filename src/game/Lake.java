package game;

import edu.monash.fit2099.demo.mars.DemoCapabilities;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class Lake extends Ground {

    private int numberOfSips = 25;
    private int numberOfFish = 5;
    private int turn = 0;

    public Lake(){
        super('~');
    }

    public void addNumberOfSips(int numberOfSips){
        this.numberOfSips += numberOfSips;
    }

    public void decNumberOfSips(){
        numberOfSips -= 1;
    }

    public int getNumberOfSips(){
        return  numberOfSips;
    }

    public void setNumberOfFish(int numberOfFish){
        this.numberOfFish += numberOfFish;
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
        super.tick(location);
        Random r = new Random();
        int fishChance = r.nextInt(100)+1;
        int rainChance = r.nextInt(100) + 1;
        double rainfall = 20 * (0.1 + (r.nextDouble() * 0.5)); //20 * (0.1~0.6)
        turn++;

        if ((turn%10) == 0 && turn > 0){ //every 10 turn
            if (rainChance <= 20){
                addNumberOfSips((int)Math.round(rainfall));
            }
        }
        if (fishChance <= 60 && numberOfFish < 25){
            numberOfFish++;
        }
    }
    }
