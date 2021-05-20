package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

/** a herbivore dinosaur
 * @author Muhammad Naqeeb Alam
 * @see Dinosaur
 * @see Bush
 * @see Tree
 * @see Following
 * @see Location
 * @see AgeGroup
 * @see BreedingState
 * @see BrachiosaurCorpse
 * @version 1.0.0
 *
 */

public class Brachiosaur extends Dinosaur {

    private DinosaurFunctionsClass dinosaurFunctionsClass;
    /**
     * Constructor.
     * All Stegosaurs are represented by a 'd' and have 100 hit points.
     *
     * @param name the name of this Stegosaur
     */
    public Brachiosaur(String name, String gender, DinosaurFunctionsClass dinosaurFunctionsClass) {
        super(name, 'e', 160,60,200,gender);
        this.dinosaurFunctionsClass=dinosaurFunctionsClass;
        hitPoints=100;

    }


    /**
     *it handles the actions made by Brchiosaur at each turn
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        setUnconsciousTurns(0);
        Random r=new Random();
        Location thisLocation=map.locationOf(this);
        Action wander = null;


        if(isConscious()){
            if (thisLocation.getGround() instanceof Tree){
                Tree tree=(Tree)thisLocation.getGround();
                for (Fruit fruits:tree.getFruits()) {
                    if (!(fruits.hasCapability(FruitStatus.DROPPED)) && fruits.getPortable()) {
                        thisLocation.removeItem(fruits);
                        System.out.println("Brachiosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") eats fruit on tree. Heal 5hp.");
                        tree.remove(fruits);
                        this.heal(5);
                    }
                }
                thisLocation.setGround(tree);
            }
            else if(thisLocation.getGround() instanceof Bush){
                if(r.nextInt(100)+1>50){
                    System.out.println("Bush at (" + thisLocation.x() + ", " +thisLocation.y() + ") is destroyed by Brachiosaur");
                    thisLocation.setGround(new Dirt());
                }

            }
            if(!hasCapability(AgeGroup.Baby)) {
                if (getGender().contentEquals("female")) {
                    if (!(hasCapability(BreedingState.Pregnant))) {
                        for (Exit exit : thisLocation.getExits()) {
                            Location destination = exit.getDestination();
                            if (destination.containsAnActor()) {
                                Actor actor = destination.getActor();
                                if (actor instanceof Brachiosaur && ((Brachiosaur) actor).getGender().contentEquals("male")) {
                                    setPregnantCount(0);
                                    addCapability(BreedingState.Pregnant);
                                    System.out.println("Brachiosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is pregnant");
                                    ((Brachiosaur)actor).setBreedingState(false); //back to false after success breeding
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() == 30) {
                            BrachiosaurEgg egg = new BrachiosaurEgg("egg", '0', false);
                            System.out.println("Brachiosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") lay an egg.");
                            egg.addCapability(eggOf.Brachiosaur);
                            thisLocation.addItem(egg);
                            removeCapability(BreedingState.Pregnant);

                        }
                    }
                }
            }
            else {
                setBabyAge(getBabyAge() + 1);
                if (getBabyAge() == 50) {
                    System.out.println("Baby brachiosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") grows into an adult");
                    removeCapability(AgeGroup.Baby);
                    setHitPoints(100);
                }
            }
            setUnconsciousTurns(0);
            if(hitPoints>70 && !hasCapability(AgeGroup.Baby)){
                int breed = r.nextInt(100)+1;
                if(breed < 70 && !this.getBreedingState()) {
                    System.out.println("Brachiosaur at (" + thisLocation.x() + ", " + thisLocation.y() + ") wants to breed.");
                    this.setBreedingState(true);
                    wander = new Following(false, true, false, false,false,false).getAction(this, map);
                }
            }
            else if(hitPoints<140){
                System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
                wander=new Following(true,false,false,false,false,false).getAction(this,map);
            }
            else if(getWaterLevel()<40){
                System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting thirsty!");
                wander=new Following(true,false,false,false,true,false).getAction(this,map);

            }
            else{
                wander=getBehaviour().getAction(this,map);
            }
            hurt(1);
            setWaterLevel(getWaterLevel()-1);
            if (wander!=null){
                return wander;
            }
        }
        else{
            System.out.println("Brachiosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is unconscious.");
            if(Sky.isRaining()){
              setWaterLevel(10);
            }
            if(!isConscious()) {
                incrementUnconsciousTurns();
                if (getUnconsciousTurns() == 15) {
                    System.out.println("Brachiosaur at (" + thisLocation.x() + ", " + thisLocation.y() + ") is dead.");
                    map.removeActor(this);
                    thisLocation.addItem(new BrachiosaurCorpse("Brachiosaur", '?'));
                }
            }
        }

        return new DoNothingAction();
    }
}
