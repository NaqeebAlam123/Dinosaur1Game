package game.dinosaurs.functions;

import edu.monash.fit2099.engine.Location;
import game.grounds.Lake;
import game.dinosaurs.live.Dinosaur;

import java.util.Random;

public class Catching extends DinosaurFunctionsClass {

    @Override
    public void catchFish(Dinosaur dinosaur, Location location) {
        Random r = new Random();
        if (location.getGround() instanceof Lake) {
            dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30);
            Lake lake = (Lake) location.getGround();
            int catchFish;
            int heal;
            if (lake.getNumberOfFish() > 0) {
                if (lake.getNumberOfFish() < 3) {
                    catchFish = r.nextInt(lake.getNumberOfFish()) + 1;
                } else {
                    catchFish = r.nextInt(3);
                }
                heal = 5 * catchFish;
                dinosaur.setHitPoints(dinosaur.getHitPoints()+heal);
                System.out.println(dinosaur.getName() + " at (" + location.x() + "," + location.y() + ") " +
                        "caught " +  catchFish + "fishes and takes a sip from the lake.");

            }
        }
    }
}