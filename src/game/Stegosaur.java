package game;


import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A herbivorous dinosaur.
 * @author Muhammad Naqeeb Alam
 * @see Dinosaur
 * @see Bush
 * @see Tree
 * @see Following
 * @see Location
 * @see AgeGroup
 * @see BreedingState
 * @see StegosaurCorpse
 * @version 1.0.0
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.

	private boolean hurt;
	private int hurtDuration;
	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name,String gender) {
		super(name, 'd', 100, 100, gender);
		setWaterLevel(60);
		hitPoints = 50;

	}
	/**
	 * A method to add a status called hurt to stegosaur
	 * @param hurt define if a Stegosaur is attacked by Allosaur or not
	 */
	public void setHurt(boolean hurt){
		this.hurt = hurt;
		hurtDuration = 20;
	}

	/**
	 * A method that return hurt status of stegosaur
	 * @return boolean of hurt status
	 */
	public boolean getHurt(){
		return hurt;
	}

	/**
	 * decrease the duration of hurt until it reach 0
	 */
	public void decrementHurtDuration(){
		if (hurtDuration > 0){
			hurtDuration -= 1;
		}
	}

	/**
	 * Returns the remaining duration of hurt status
	 * @return remaining duration of hurt status
	 */
	public int getHurtDuration(){
		return hurtDuration;
	}


	/**
	 *it handles the actions made by Stegosaur at each turn
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Location thisLocation=map.locationOf(this);
		FoodSource foodSource=null;
		Action wander = null;
		boolean drink = false;
		Lake lake = null;


		if(isConscious() && getWaterLevel() > 0){


			for (Exit exit : thisLocation.getExits()) {
				Location destination = exit.getDestination();
				if (destination.getGround() instanceof Lake) {
					lake = (Lake) destination.getGround();
					drink = true;
					break;
				}
			}

			if(drink){
				addWaterLevel(30);
				lake.decNumberOfSips();
			}
			if(thisLocation.getGround() instanceof Tree){
				foodSource=(Tree)thisLocation.getGround();

			}
			else if(thisLocation.getGround() instanceof Bush){
				foodSource=(Bush)thisLocation.getGround();
			}

			if (this.getHurt()){ // if it is hurt by allosaur before
				if(this.getHurtDuration() == 0){ //counter is up, set hurt to false(healed up)
					this.setHurt(false);
				}else{
				this.decrementHurtDuration(); //decrease counter every turn
				}
			}
			if(foodSource!=null){
				for (Fruit fruits:foodSource.getFruits()) {
					if (fruits.hasCapability(FruitStatus.DROPPED)) {
						thisLocation.removeItem(fruits);
						foodSource.remove(fruits);
						System.out.println("Stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") eats fruits and heal 10hp");
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
							if (destination.containsAnActor() && getBreedingState()) {
								Actor actor = destination.getActor();
								if (actor instanceof Stegosaur && ((Stegosaur) actor).getGender().contentEquals("male")) {
									setPregnantCount(0);
									System.out.println("Stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is pregnant");
									addCapability(BreedingState.Pregnant);
									((Stegosaur)actor).setBreedingState(false); //back to false after success breeding
									break;
								}

							}
						}
					} else {
						incrementPregnantCount();
						if (getPregnantCount() == 10) {
							System.out.println("Stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") lay an egg.");
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
					System.out.println("Baby stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") grows into an adult.");
					removeCapability(AgeGroup.Baby);
					setHitPoints(50);
				}
			}
			setUnconsciousTurns(0);
			if(hitPoints>50 && !hasCapability(AgeGroup.Baby)){
				Random r = new Random();
				int breed = r.nextInt(100)+1;
				if(breed < 99 && !this.getBreedingState()) {
					System.out.println("Stegosaur at (" + thisLocation.x() + ", " + thisLocation.y() + ") wants to breed.");
					this.setBreedingState(true);
					wander = new Following(false, true, false, false, false, false).getAction(this, map);
				}
			}
			else if(hitPoints<90){
				System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
				wander=new Following(true,false,false,false, false, false).getAction(this,map);
			}else if(getWaterLevel()<50){

				System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting thirsty!");

				wander=new Following(false,false,false,false,true, false).getAction(this,map);
			}
			else{
				wander=getBehaviour().getAction(this,map);
			}

			// decrease food level and water level
			thirsty();
			hurt(1);
			if (wander!=null){
				return wander;
			}
		}
		else{
			System.out.println("Stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is unconscious.");
			incrementUnconsciousTurns();
			if(getUnconsciousTurns()==2){
				System.out.println("Stegosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is dead.");
				map.removeActor(this);
				thisLocation.addItem(new StegosaurCorpse("Stegosaur",'?'));
			}
		}
		
		return new DoNothingAction();
	}


}
