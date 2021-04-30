package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Item,VendingMachine,Tree,Bush
 */
public class Fruit extends Item {
    /**
     * a final value which is the points require to purchase fruits in Vending Machine
     */
    public final int ecoPoints = 30;
    /**
     * age of fruit
     */
    private int fruitAge;

    /**
     * Initializes name ,display character and portable(boolean value indicating whether can be picked up or not)
     * @param name
     * @param displayChar display Character
     * @param portable boolean value( True or False)
     */
    public Fruit(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * It initialises the fruit age to be 1 when the fruit is dropped or riped in case of bushes
     */
    public void dropFruit() {
        fruitAge = 1;
    }

    /**
     * It increases the fruit age by 1
     */

    public void ageFruit() {
        fruitAge = fruitAge + 1;
    }

    /**
     * Change the portable value
     * @param portable
     */
    public void setPortable(boolean portable) {
        this.portable = portable;
    }

    /**
     * get portable value
     * @return
     */
    public boolean getPortable() {
        return portable;
    }

    /**
     * get the age of fruit
     * @return
     */

    public int getFruitAge() {
        return fruitAge;
    }
}
