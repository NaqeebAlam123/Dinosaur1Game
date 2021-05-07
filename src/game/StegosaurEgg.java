package game;

import edu.monash.fit2099.engine.Item;

/**
 * Stegosaur egg class extending Egg class
 * @author Muhammad Naqeeb Alam
 * @see Egg
 * @see Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @see Stegosaur
 * @version 1.0.0
 */
public class StegosaurEgg extends Egg{
    /**
     * a final value which is the points require to purchase StegosaurEgg in Vending Machine
     */
    public final int ecoPoints=200;
    public StegosaurEgg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
