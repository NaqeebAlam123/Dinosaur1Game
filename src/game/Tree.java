package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Muhammad Naqeeb Alam
 * @see Fruit,Ground,Util,Location
 * @version 1.0.0
 */
public class Tree extends Ground {
	/**
	 * age of Tree
	 */
	private int age = 0;

	/**
	 * Return list of fruit objects
	 * @return
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * Remove specific fruit object from list of fruits
	 * @param fruit fruit object
	 */
	public void remove(Fruit fruit){
		fruits.remove(fruit);
	}

	/**
	 * Holds list of fruit objects
	 */
	private ArrayList<Fruit> fruits=new ArrayList<>();

	/**
	 * Returns the number of hanging Fruits
	 * @return
	 */
	public int getHangingFruits() {
		return hangingFruits;
	}

	/**
	 * number of hanging fruits
	 */
	private int hangingFruits;
	public Tree() {
		super('+');
	}

	/**
	 * This method is invoked when ground at which GameMap is traversing is a Tree
	 * Add ripe fruits to Tree
	 * Fruits gets dropped or rotten
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		Random r =new Random();
		age++;


		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	    if (r.nextInt(100)+1>50){
	    	fruits.add(new Fruit("apple",'a',true));
	    	GameMap map=location.map();
	    	Player player=Util.findPlayer(map);
			player.incrementPoints(1);
	    	hangingFruits=hangingFruits+1;
		}
	    for(int i=0;i<=fruits.size()-1;i++){
	    	Fruit thisFruit=fruits.remove(i);
	    	if (thisFruit.hasCapability(FruitStatus.DROPPED) ){
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
	    	else if(!(thisFruit.hasCapability(FruitStatus.ROTTEN))){
				if (r.nextInt(100)+1>95){
					thisFruit.addCapability(FruitStatus.DROPPED);
					thisFruit.dropFruit();
					thisFruit.setPortable(true);
					location.addItem(thisFruit);
					hangingFruits=hangingFruits-1;

				}

				fruits.add(i,thisFruit);

			}
		}




	}
}
