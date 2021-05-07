package game;

import edu.monash.fit2099.engine.Item;
/**
 * An item that can be fed to herbivore dinosaurs which will heal them up fully
 * @author Muhammad Naqeeb Alam
 * @see Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class vegetarianMealKit extends Item {
    /**
     * a final value which is the points require to purchase vegetarianMealKit in Vending Machine
     */
    public final int ecoPoints=100;

    public vegetarianMealKit() {
        super("VegetarianMealKit",'i',true);

    }
}
