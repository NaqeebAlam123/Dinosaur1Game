package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.HashMap;

public abstract class Dinosaur extends Actor {

    private Behaviour behaviour;
    private boolean hurt;
    private int hurtDuration;
    private int unconsciousTurns=0;
    private int babyAge=0;
    private String gender;
    private String target;


    public int getBabyAge() {
        return babyAge;
    }

    public void setBabyAge(int babyAge) {
        if(babyAge >= 0){
        this.babyAge = babyAge;
        }
    }


    public Behaviour getBehaviour() {
        return behaviour;
    }
    public int getMaxHitPoints(){
        return maxHitPoints;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public void setHitPoints(int hitPoints){
        this.hitPoints=hitPoints;
    }

    public int getUnconsciousTurns() {
        return unconsciousTurns;
    }

    public int getPregnantCount() {
        return pregnantCount;
    }

    public boolean isBreedingState() {
        return breedingState;
    }

    public void setUnconsciousTurns(int unconsciousTurns) {
        this.unconsciousTurns = unconsciousTurns;
    }

    public void setPregnantCount(int pregnantCount) {
        this.pregnantCount = pregnantCount;
    }

    public void setBreedingState(boolean breedingState) {
        this.breedingState = breedingState;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    public void incrementPregnantCount(){
        pregnantCount+=1;
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


    private int pregnantCount;
    private boolean breedingState=false;
    public String getGender() {
        return gender;
    }



    public String getTarget() {
        return target;
    }


    public void incrementUnconsciousTurns(){
        unconsciousTurns=unconsciousTurns+1;
    }

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

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions=new Actions();
        actions.add(new AttackAction(this));
        if (otherActor instanceof Player){
            actions.add(new FeedDinosaur(this));}

        return actions;
    }
}
