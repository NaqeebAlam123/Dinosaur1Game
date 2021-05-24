package game.dinosaurs.eggs;

import game.dinosaurs.eggs.Egg;

public class PterodactylsEgg extends Egg {
    /**
     * a final value which is the points require to purchase PterodactylsEgg in Vending Machine
     */
    public final int ecoPoints= 200;
    public PterodactylsEgg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

}
