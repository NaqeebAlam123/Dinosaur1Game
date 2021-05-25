package game.dinosaurs.functions;

import edu.monash.fit2099.engine.Location;
import game.grounds.Lake;
import game.dinosaurs.live.Dinosaur;

import java.util.Random;

/**
 * This class modifies Pterodactyls as it can only catch fish
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see DinosaurFunctionsClass
 * @see Dinosaur
 * @see game.dinosaurs.eggs.Egg
 * @see DinosaurFunctions
 * @see game.dinosaurs.live.Pterodactyls

 */
public class Catching extends DinosaurFunctionsClass {
    /**
     * allows catching of fish if the flying dinosaur is at ground of lake
     * @param dinosaur Dinosaur instance
     * @param location current location
     */
    @Override
    public void catchFish(Dinosaur dinosaur, Location location) {
        Random r = new Random();
        if (location.getGround() instanceof Lake) { // if current location is an instance of lake
            dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30); //increase the water level by 30
            Lake lake = (Lake) location.getGround();
            int catchFish;
            int heal;
            if (lake.getNumberOfFish() > 0) { // if the number of fishes in lake are greater than 0
                if (lake.getNumberOfFish() < 3) { // and less than 3
                    catchFish = r.nextInt(lake.getNumberOfFish()) + 1;//than catch fish between 1 and number of fishes in lake
                } else { //othewise catch fishes less than 3
                    catchFish = r.nextInt(3);
                }
                heal = 5 * catchFish; //heal points to be added are calculated using this and added to dinosaur hit points
                dinosaur.setHitPoints(dinosaur.getHitPoints()+heal);
                System.out.println(dinosaur.getName() + " at (" + location.x() + "," + location.y() + ") " +
                        "caught " +  catchFish + "fishes and takes a sip from the lake.");

            }
        }
    }
}