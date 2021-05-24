package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class Drink extends DinosaurFunctionsClass{

    public void drink(Dinosaur dinosaur,Location location){
        for(Exit exit:location.getExits()){
            Location destination=exit.getDestination();
            if(destination.getGround() instanceof Lake){
                Lake lake=(Lake) destination.getGround();
                lake.setNumberOfSips(lake.getNumberOfSips()-1);
                destination.setGround(lake);
                if(dinosaur instanceof Brachiosaur){
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
