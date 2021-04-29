package game;

import edu.monash.fit2099.engine.Item;

/**
 * @author Muhammad Naqeeb Alam
 * @see Item
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
