package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
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

	public   void incrementPoints(int points){
		ecoPoints=ecoPoints+points;
	}
	public void deductPoints(int points){ecoPoints=ecoPoints-points;}

	public int getEcoPoints() {
		return ecoPoints;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		Location thisLocation=map.locationOf(this);
		/*for (Exit exit : thisLocation.getExits()) {
			Location destination = exit.getDestination();*/

		if (thisLocation.getGround() instanceof Tree){
			Tree tree=(Tree) thisLocation.getGround();


				for (Fruit fruits:tree.getFruits()) {
					if (!(fruits.hasCapability(FruitStatus.DROPPED)) && fruits.getPortable()) {
						actions.add(new HarvestingFruits(fruits));
					}
				}
			if (actions.size() == 0) {
				System.out.println("Player searched the tree but couldnot found riped ones");

			}
			}

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}
}
