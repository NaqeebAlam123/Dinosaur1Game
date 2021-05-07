package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;


/**
 * A class that represents bare dirt.
 * @author Muhammad Naqeeb Alam
 * @see Tree
 * @see Ground
 * @see Location
 * @see edu.monash.fit2099.engine.Exit
 * @see Bush
 * @version 1.0.0

 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	/**
	 * This method is invoked when ground at which GameMap is traversing is a Dirt
	 * Sets Bush to ground under specified conditions
	 * @param location The location of the Ground
	 */
	public void tick(Location location){
		super.tick(location);
		Random r =new Random();
		int [] num=returnTreesAndBushes(location);
		if (num[1]<=0){
			if (num[0]>=2) {
				if (r.nextInt(100) + 1 > 90) {
					location.setGround(new Bush());
				}

			}
			else if(r.nextInt(100) + 1 > 99){
				location.setGround(new Bush());
			}

		}

	}

	/**
	 * This method returns number of bushes and number of trees it is next to in form of list
	 * @param location stores the current location
	 * @return return number of bushes and number of trees
	 */
	public int [] returnTreesAndBushes(Location location){
		int t=0;
		int b=0;
		for (int i=0;i<location.getExits().size();i++){
			if (location.getExits().get(i).getDestination().getGround() instanceof Bush){
				b=b+1;
			}
			else if (location.getExits().get(i).getDestination().getGround() instanceof Tree){
				t=t+1;
			}
		}
		return (new int[]{b,t});

	}
}
