package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public abstract class Dinosaur extends Actor {
    private Behaviour behaviour;
    private int unconsciousTurns=0;

    public int getBabyAge() {
        return babyAge;
    }

    public void setBabyAge(int babyAge) {
        this.babyAge = babyAge;
    }

    private int babyAge=0;

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
        pregnantCount=pregnantCount+1;
    }

    private int pregnantCount;
    private boolean breedingState=false;
    public String getGender() {
        return gender;
    }





    private String gender;

    public String getTarget() {
        return target;
    }

    private String target;
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
