package game;

import edu.monash.fit2099.engine.Ground;

import java.util.ArrayList;

/**
 * @author Muhammad Naqeeb Alam
 * @see Ground
 */
public class FoodSource extends Ground {
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
     * @return
     */
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    /**
     * Removing fruit from list of fruits
     * @param fruit
     */
    public void remove(Fruit fruit){
        fruits.remove(fruit);
    }

    /**
     * Adding fruit to list of fruits
     * @param fruit
     */
    public void add(Fruit fruit){
        fruits.add(fruit);
    }
}
