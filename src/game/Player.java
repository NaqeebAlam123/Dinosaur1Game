package game;

import edu.monash.fit2099.engine.*;

/**
 * @author Muhammad Naqeeb Alam
 * @see Tree
 * @see Bush
 * @see Fruit
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	/**
	 * Stores eco points of players
	 */

	private int ecoPoints=0;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	/**
	 * increment eco points
	 * @param points
	 */
	public   void incrementPoints(int points){
		ecoPoints=ecoPoints+points;
	}
	/**
	 * Deduct eco points
	 * @param points
	 */
	public void deductPoints(int points){ecoPoints=ecoPoints-points;}
	/**
	 * get eco points
	 */
	public int getEcoPoints() {
		return ecoPoints;
	}

	/**
	 * return actions that the Player can do
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		Actions inActions=new Actions();
		Location thisLocation=map.locationOf(this);
		FoodSource foodSource=null;
		if(thisLocation.getGround() instanceof Tree){
			foodSource=(Tree)thisLocation.getGround();

		}
		else if(thisLocation.getGround() instanceof Bush){
			foodSource=(Bush)thisLocation.getGround();
		}
        if (foodSource!=null){
				for (Fruit fruits:foodSource.getFruits()) {
					if ((!(fruits.hasCapability(FruitStatus.DROPPED)) && fruits.getPortable())||(foodSource instanceof Bush && fruits.hasCapability(FruitStatus.DROPPED))) {
						actions.add(new HarvestingFruits(fruits));
					}
				}
			if (actions.size() == 0) {
				System.out.println("Player searched the tree or bush but couldnot found riped ones");

			}
        }

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}
}
