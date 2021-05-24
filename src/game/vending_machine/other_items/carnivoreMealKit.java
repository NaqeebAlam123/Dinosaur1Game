package game.vending_machine.other_items;

import edu.monash.fit2099.engine.Item;
import game.player.actions.PickupFromVendingMachine;
import game.vending_machine.VendingMachine;

/**
 * kit for healing a carnivore dinosaur fully
 * @author Muhammad Naqeeb Alam
 * @see Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class carnivoreMealKit extends Item {
    /**
     * a final value which is the points require to purchase carnivoreMealKit in Vending Machine
     */
    public final int ecoPoints=100;

    public carnivoreMealKit() {
        super("carnivoreMealKit",'i',true);

    }
}
