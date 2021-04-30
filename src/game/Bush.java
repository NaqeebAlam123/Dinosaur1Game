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
public class Bush extends FoodSource {

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
            location.addItem(thisFruit);
            add(thisFruit);

        }
        for(int i=0;i<=getFruits().size()-1;i++){
            Fruit thisFruit=getFruits().get(i);
            remove(thisFruit);

            if(thisFruit.hasCapability(FruitStatus.DROPPED)){
                if (thisFruit.getFruitAge() >14) {
                    thisFruit.removeCapability(FruitStatus.DROPPED);
                    thisFruit.addCapability(FruitStatus.ROTTEN);
                    thisFruit.setPortable(false);
                    location.removeItem(thisFruit);
                }
                else{

                    thisFruit.ageFruit();

                    add(thisFruit);
                }
            }

        }
    }
}
