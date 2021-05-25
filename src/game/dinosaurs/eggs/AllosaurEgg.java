package game.dinosaurs.eggs;

import game.player.actions.PickupFromVendingMachine;
import game.vending_machine.VendingMachine;
import game.dinosaurs.live.Allosaur;

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

    /**
     * Constructor For Allosaur Egg
     * @param name Name of the egg
     * @param displayChar display character of the egg
     * @param portable portability of the egg
     */
        public AllosaurEgg(String name, char displayChar, boolean portable) {
            super(name, displayChar, portable);
        }
}
