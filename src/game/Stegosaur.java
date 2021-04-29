package game;


import edu.monash.fit2099.engine.*;

/**
 * A herbivorous dinosaur.
 * @author Muhammad Naqeeb Alam
 * @see Dinosaur
 * @version 1.0.0
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.


	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name,String gender) {
		super(name, 'd', 100, gender);

		hitPoints = 50;

	}

	/**
	 *it handles the actions made by Stegosaur at each turn
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Location thisLocation=map.locationOf(this);
		FoodSource foodSource=null;

		Action wander ;
		if(isConscious()){
			if(thisLocation.getGround() instanceof Tree){
				foodSource=(Tree)thisLocation.getGround();
			}
			else if(thisLocation.getGround() instanceof Bush){
				foodSource=(Bush)thisLocation.getGround();

			}
			if(foodSource!=null){
				for (Fruit fruits:foodSource.getFruits()) {
					if (fruits.hasCapability(FruitStatus.DROPPED)) {
						thisLocation.removeItem(fruits);
						foodSource.remove(fruits);
						this.heal(10);
						break;
					}
				}
				thisLocation.setGround(foodSource);
			}
			if(!hasCapability(AgeGroup.Baby)) {
				if (getGender().contentEquals("female")) {
					if (!(hasCapability(BreedingState.Pregnant))) {
						for (Exit exit : thisLocation.getExits()) {
							Location destination = exit.getDestination();
							if (destination.containsAnActor()) {
								Actor actor = destination.getActor();
								if (actor instanceof Stegosaur && ((Stegosaur) actor).getGender().contentEquals("male")) {
									setPregnantCount(0);
									addCapability(BreedingState.Pregnant);
									break;
								}

							}
						}
					} else {
						incrementPregnantCount();
						if (getPregnantCount() == 10) {
							StegosaurEgg egg = new StegosaurEgg("egg", '0', false);
							egg.addCapability(eggOf.Stegosaur);
							thisLocation.addItem(egg);
							removeCapability(BreedingState.Pregnant);

						}
					}
				}
			}
			else{
				setBabyAge(getBabyAge()+1);
				if (getBabyAge()==30){
					removeCapability(AgeGroup.Baby);
				}
			}
			setUnconsciousTurns(0);
			if(hitPoints>50){
				wander=new Following(false,true,false).getAction(this,map);
			}
			else if(hitPoints<90){
				System.out.println(this.name+"at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
				wander=new Following(true,false,false).getAction(this,map);;
			}
			else{
				wander=getBehaviour().getAction(this,map);
			}
			hurt(1);
			if (wander!=null){
				return wander;
			}
		}
		else{
			incrementUnconsciousTurns();;
			if(getUnconsciousTurns()==20){
				map.removeActor(this);
				thisLocation.addItem(new StegosaurCorpse("Stegosaur",'?'));
			}
		}
		
		return new DoNothingAction();
	}

}
