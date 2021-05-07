package game;
/**
 * brachiosaur egg class extending the Egg class
 * @author Muhammad Naqeeb Alam
 * @see Egg
 * @see Brachiosaur
 * @see edu.monash.fit2099.engine.Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class BrachiosaurEgg extends Egg{
    /**
     * a final value which is the points require to purchase BrachiosaurEgg in Vending Machine
     */
    public final int ecoPoints=500;
    public BrachiosaurEgg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
