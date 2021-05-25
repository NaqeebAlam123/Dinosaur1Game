package game.dinosaurs.functions;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.Application;
import game.grounds.Lake;
import game.dinosaurs.live.Brachiosaur;
import game.dinosaurs.live.Dinosaur;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see DinosaurFunctionsClass
 * @see Application
 */
public class Drink extends DinosaurFunctionsClass{
    /**
     * Constructor for drink class
     * @param dinosaur Dinosaur
     * @param location location of dinosaur
     */
    public void drink(Dinosaur dinosaur, Location location){
        for(Exit exit:location.getExits()){
            Location destination=exit.getDestination();
            if(destination.getGround() instanceof Lake){
                Lake lake=(Lake) destination.getGround();
                lake.setNumberOfSips(lake.getNumberOfSips()-1);
                destination.setGround(lake);
                if(dinosaur instanceof Brachiosaur){
                    //increase the water level of dinosaurs
                    dinosaur.setWaterLevel(dinosaur.getWaterLevel()+80);
                    System.out.println(dinosaur.getName() + " at (" + destination.x() + "," + destination.y() + ") takes a sip from the lake.");
                }
                else{
                    dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30);
                    System.out.println(dinosaur.getName() + " at (" + destination.x() + "," + destination.y() + ") takes a sip from the lake.");
                }

            }

        }
    }
}
