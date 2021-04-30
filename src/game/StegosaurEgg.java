package game;

/**
 * @author Muhammad Naqeeb Alam
 * @see Egg
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
