package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see DinosaurFunctionsClass
 * @see Application
 */
public class Drink extends DinosaurFunctionsClass{
    /**
     *
     * @param dinosaur
     * @param location
     */
    public void drink(Dinosaur dinosaur,Location location){
        for(Exit exit:location.getExits()){
            Location destination=exit.getDestination();
            if(destination.getGround() instanceof Lake){
                Lake lake=(Lake) destination.getGround();
                lake.setNumberOfSips(lake.getNumberOfSips()-1);
                destination.setGround(lake);
                if(dinosaur instanceof Brachiosaur){
                    dinosaur.setWaterLevel(dinosaur.getWaterLevel()+80);
                }
                else{
                    dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30);
                }

            }

        }
    }
}
