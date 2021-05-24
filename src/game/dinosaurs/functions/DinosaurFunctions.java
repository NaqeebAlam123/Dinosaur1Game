package game.dinosaurs.functions;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.live.Dinosaur;

public interface DinosaurFunctions {
    public void drink(Dinosaur dinosaur, Location location);

    public void catchFish(Dinosaur dinosaur,Location location);
}
