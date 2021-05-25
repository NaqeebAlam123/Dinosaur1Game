package game.grounds;

import edu.monash.fit2099.engine.Ground;
import game.vending_machine.other_items.Fruit;

import java.util.ArrayList;

/**
 * a super class for Tree and Bush class
 * @author Muhammad Naqeeb Alam
 * @see Ground
 * @see Fruit
 * @version 1.0.0
 */
public class FoodSource extends Ground {
    /**
     * Constructor of FoodSource
     * @param displayChar display character for it
     */
    public FoodSource(Character displayChar){
        super(displayChar);
    }
    /**
     * Return list of fruit objects
     */
    private ArrayList<Fruit> fruits=new ArrayList<>();
    /**
     * Remove specific fruit object from list of fruits
     * @param fruit fruit object
     */

    /**
     * Return list of fruit objects
     * @return return a list of fruits
     */
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    /**
     * Removing fruit from list of fruits
     * @param fruit fruit item
     */
    public void remove(Fruit fruit){
        fruits.remove(fruit);
    }

    /**
     * Adding fruit to list of fruits
     * @param fruit fruit item
     */
    public void add(Fruit fruit){
        fruits.add(fruit);
    }
}
