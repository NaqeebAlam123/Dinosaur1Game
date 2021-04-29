package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Muhammad Naqeeb Alam
 * @see Ground,Fruit,Location
 * @version 1.0.0
 */
public class Bush extends Ground {
    /**
     * Return list of fruit objects
     */
    private ArrayList<Fruit> fruits=new ArrayList<>();
    /**
     * Remove specific fruit object from list of fruits
     * @param fruit fruit object
     */
    public void remove(Fruit fruit){
        fruits.remove(fruit);
    }
    public Bush() {
        super('b');
    }
    /**
     * This method is invoked when ground at which GameMap is traversing is a Bush
     * Add ripe fruits to Bush
     * Fruits gets rotten if there for a long time
     * @param location The location of the Ground
     */
    public void tick(Location location){
        Random r =new Random();
        if (r.nextInt(100)+1>90){
            Fruit thisFruit=new Fruit("apple",'a',true);
            thisFruit.dropFruit();
            thisFruit.addCapability(FruitStatus.DROPPED);
            fruits.add(thisFruit);

        }
        for(int i=0;i<=fruits.size()-1;i++){
            Fruit thisFruit=fruits.remove(i);
            if(thisFruit.hasCapability(FruitStatus.DROPPED)){
                if (thisFruit.getFruitAge() >14) {
                    thisFruit.removeCapability(FruitStatus.DROPPED);
                    thisFruit.addCapability(FruitStatus.ROTTEN);
                    thisFruit.setPortable(false);
                    location.removeItem(thisFruit);
                }
                else{

                    thisFruit.ageFruit();

                    fruits.add(i,thisFruit);
                }
            }

        }
    }
}
