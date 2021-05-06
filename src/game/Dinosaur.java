package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.HashMap;

public abstract class Dinosaur extends Actor {

    private Behaviour behaviour;
    private int unconsciousTurns=0;
    private int babyAge=0;
    private String gender;
    private String target;
    private int pregnantCount;
    private boolean breedingState=false;


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
        if(pregnantCount >= 0){
        this.pregnantCount = pregnantCount;
        }else{
            System.out.println("invalid pregnant count");
        }
    }

    public void setBreedingState(boolean breedingState) {
        if (breedingState || !breedingState) {
            this.breedingState = breedingState;
        }
    }

    public boolean getBreedingState(){
        return breedingState;
    }

    public void setGender(String gender) {
        if(gender.equals("M")|| gender.equals("m")||gender.equals("F")||gender.equals("f")||gender.equals("male")
        ||gender.equals("female")){
        this.gender = gender;}
        else{
            System.out.println("Invalid gender.");
        }
    }

    public void setTarget(String target) {
        this.target = target;
    }
    public void incrementPregnantCount(){
        pregnantCount+=1;
    }


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
