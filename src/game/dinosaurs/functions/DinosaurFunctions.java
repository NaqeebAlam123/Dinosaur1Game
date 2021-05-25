package game.dinosaurs.functions;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.live.Dinosaur;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see DinosaurFunctionsClass
 * @see game.dinosaurs.live.Stegosaur
 * @see game.dinosaurs.live.Allosaur
 * @see game.dinosaurs.live.Brachiosaur
 * @see game.dinosaurs.live.Pterodactyls
 */
public interface DinosaurFunctions {
    /**
     * Allows the dinosaur to drink
     * @param dinosaur
     * @param location
     */
    public void drink(Dinosaur dinosaur, Location location);

    /**
     * Allows the dinosaur to catch fish
     * @param dinosaur
     * @param location
     */
    public void catchFish(Dinosaur dinosaur,Location location);
}
