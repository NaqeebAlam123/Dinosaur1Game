package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.HashMap;

/**
 * a super class for all the dinosaur classes
 * @author Muhammad Naqeeb Alam
 * @see Actor
 * @see FeedDinosaur
 * @see Player
 * @see AttackAction
 * @version 1.0.0
 */
public abstract class Dinosaur extends Actor {
    /**
     * Behaviour object to determine the movement of the dinosaur
     */
    private Behaviour behaviour;
    /**
     * number of turns dinosaur has remain unconscious
     */
    private int unconsciousTurns=0;
    /**
     * Age of baby dinosaur
     */
    private int babyAge=0;
    /**
     * dinosaur gender
     */
    private String gender;
    /**
     * mating dinosaur gender
     */
    private String target;
    /**
     * number of turns dinosaur remains pregnant
     */
    private int pregnantCount;
    /**
     * indicates if dinosaur is in breeding state
     */
    private boolean breedingState=false;

    /**
     * Gets age of baby
     * @return
     */
    public int getBabyAge() {
        return babyAge;
    }

    /**
     * Sets baby age
     * @param babyAge
     */
    public void setBabyAge(int babyAge) {
        if(babyAge >= 0){
        this.babyAge = babyAge;
        }
    }

    /**
     * Gets behaviour object
     * @return
     */
    public Behaviour getBehaviour() {
        return behaviour;
    }

    /**
     * gets maximum food value for the particular dinosaur
     * @return
     */
    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    /**
     * gets current food value for the particular dinosaur
     * @return
     */
    public int getHitPoints(){
        return hitPoints;
    }

    /**
     * sets current food value for the particular dinosaur
     * @param hitPoints
     */
    public void setHitPoints(int hitPoints){
        this.hitPoints=hitPoints;
    }

    /**
     * gets number of turns dinosaur has remian unconscious
     * @return
     */
    public int getUnconsciousTurns() {
        return unconsciousTurns;
    }

    /**
     * gets number of turns dinosaur has remian pregnant
     * @return
     */
    public int getPregnantCount() {
        return pregnantCount;
    }

    /**
     * returns value indicating that dinosaur is in breeding state
     * @return
     */
    public boolean isBreedingState() {
        return breedingState;
    }

    /**
     * set unconscious turns for dinosaur
     * @param unconsciousTurns
     */
    public void setUnconsciousTurns(int unconsciousTurns) {
        this.unconsciousTurns = unconsciousTurns;
    }

    /**
     * set pregnant count for the dinosaur
     * @param pregnantCount
     */
    public void setPregnantCount(int pregnantCount) {
        if(pregnantCount >= 0){
        this.pregnantCount = pregnantCount;
        }else{
            System.out.println("invalid pregnant count");
        }
    }

    /**
     * sets breeding state
     * @param breedingState
     */
    public void setBreedingState(boolean breedingState) {
        if (breedingState || !breedingState) {
            this.breedingState = breedingState;
        }
    }

    /**
     * gets breeding state
     * @return
     */
    public boolean getBreedingState(){
        return breedingState;
    }

    /**
     * sets gender for particular dinosaur
     * @param gender
     */
    public void setGender(String gender) {
        if(gender.equals("M")|| gender.equals("m")||gender.equals("F")||gender.equals("f")||gender.equals("male")
        ||gender.equals("female")){
        this.gender = gender;}
        else{
            System.out.println("Invalid gender.");
        }
    }

    /**
     * sets the target gender dinosaur needs to look for mating
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * increment pregnant turns
     */
    public void incrementPregnantCount(){
        pregnantCount+=1;
    }

    /**
     * gets gender
     * @return
     */
    public String getGender() {
        return gender;
    }


    /**
     * gets the target gender
     * @return
     */
    public String getTarget() {
        return target;
    }

    /**
     * increment unconscious turns
     */
    public void incrementUnconsciousTurns(){
        unconsciousTurns=unconsciousTurns+1;
    }

    /**
     * Constructor for dinosaur that initialises its name, display character on map, initial food values and its gender
     * @param name
     * @param displayChar
     * @param hitPoints
     * @param gender
     */
    public Dinosaur(String name, char displayChar, int hitPoints,String gender) {
        super(name, displayChar, hitPoints);
        this.gender=gender;
        if (gender.contentEquals("male")){
            target="female";
        }
        else{
            target="male";
        }

        behaviour = new WanderBehaviour();;
    }

    /**
     * gets Actions that other actor( dinosaur or player) can do on this dinosaur , returns list of actions objects
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions=new Actions();
        actions.add(new AttackAction(this));
        if (otherActor instanceof Player){
            actions.add(new FeedDinosaur(this));}

        return actions;
    }
}
