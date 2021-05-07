package game;

/**
 * Allosaur Egg class extending Egg class
 *@author Cheng Zi Ming
 * @see Egg
 * @see Allosaur
 * @see edu.monash.fit2099.engine.Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class AllosaurEgg extends Egg{

        /**
         * a final value which is the points require to purchase StegosaurEgg in Vending Machine
         */
        public final int ecoPoints=1000;
        public AllosaurEgg(String name, char displayChar, boolean portable) {
            super(name, displayChar, portable);
        }
}
