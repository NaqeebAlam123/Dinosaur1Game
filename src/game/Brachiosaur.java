package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Brachiosaur extends Dinosaur {


    /**
     * Constructor.
     * All Stegosaurs are represented by a 'd' and have 100 hit points.
     *
     * @param name the name of this Stegosaur
     */
    public Brachiosaur(String name,String gender) {
        super(name, 'e', 160,gender);
        hitPoints=100;

    }


    /**
     *it handles the actions made by Brchiosaur at each turn
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Random r=new Random();
        Location thisLocation=map.locationOf(this);
        Action wander = null;


        if(isConscious()){
            if (thisLocation.getGround() instanceof Tree){
                Tree tree=(Tree)thisLocation.getGround();
                for (Fruit fruits:tree.getFruits()) {
                    if (!(fruits.hasCapability(FruitStatus.DROPPED)) && fruits.getPortable()) {
                        thisLocation.removeItem(fruits);
                        tree.remove(fruits);
                        this.heal(5);
                    }
                }
                thisLocation.setGround(tree);
            }
            else if(thisLocation.getGround() instanceof Bush){
                if(r.nextInt(100)+1>50){
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
                                    this.setBreedingState(false); //back to false after success breeding
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() == 30) {
                            BrachiosaurEgg egg = new BrachiosaurEgg("egg", '0', false);
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
                    removeCapability(AgeGroup.Baby);
                }
            }
            setUnconsciousTurns(0);
            Following m;
            if(hitPoints>70 && !hasCapability(AgeGroup.Baby)){
                int breed = r.nextInt(100);
                if(breed < 30 && !this.getBreedingState()) {
                    this.setBreedingState(true);
                    wander = new Following(false, true, false, false).getAction(this, map);
                }
            }
            else if(hitPoints<140){
                System.out.println(this.name+"at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
                wander=new Following(true,false,false,false).getAction(this,map);
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
            incrementUnconsciousTurns();
            if(getUnconsciousTurns()==15){
                map.removeActor(this);
                thisLocation.addItem(new StegosaurCorpse("Brachiosaur",'?'));
            }
        }

        return new DoNothingAction();
    }
}
