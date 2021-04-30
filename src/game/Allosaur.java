package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A carnivorous Dinosaur - Allosaur
 * @Author Cheng Zi Ming
 * @see Dinosaur
 * @version 1.0.0
 */
public class Allosaur extends Dinosaur {
        // Will need to change this to a collection if Stegosaur gets additional Behaviours.


        /**
         * Constructor.
         * All Allosaur are represented by a 'd' and have 100 hit points.
         *
         * @param name the name of this Allosaur
         */
        public Allosaur(String name,String gender) {
            super(name, 'd', 100, gender);

            hitPoints = 50;

        }

    /**
     *it handles the actions made by Allosaur at each turn
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Random r=new Random();
        Location thisLocation=map.locationOf(this);
        Action wander ;
        if(isConscious()){
            if(thisLocation.getItems() instanceof Corpse){ //if it is a corpse
                Corpse food = (Corpse) thisLocation.getItems();
                if (food instanceof AllosaurCorpse || food instanceof StegosaurCorpse){
                    this.heal(50);
                    thisLocation.removeItem(food);
                }else if(food instanceof BrachiosaurCorpse){
                    this.heal(100);
                    thisLocation.removeItem(food);
                }
            }else if(thisLocation.getItems() instanceof Egg){
                Egg food = (Egg) thisLocation.getItems();
                this.heal(10);
                thisLocation.removeItem(food);
            }else if(thisLocation.getActor() instanceof Stegosaur){
                    Stegosaur stegosaur = (Stegosaur) thisLocation.getActor();
                    if(!stegosaur.getHurt()){ // If stegosaur is not hurt
                        this.heal(20);
                        stegosaur.hurt(20); //attack stegosaur
                        stegosaur.setHurt(true);
                        if(stegosaur.getHitPoints() <= 0){
                            this.heal(50);
                            ActorLocations actorLocations = new ActorLocations();
                            actorLocations.remove(stegosaur); //remove dead stegosaur
                        }
                    }
            }
            if(!hasCapability(AgeGroup.Baby)) {
                if (getGender().contentEquals("female")) {
                    if (!(hasCapability(BreedingState.Pregnant))) {
                        for (Exit exit : thisLocation.getExits()) {
                            Location destination = exit.getDestination();
                            if (destination.containsAnActor()) {
                                Actor actor = destination.getActor();
                                if (actor instanceof Allosaur && ((Allosaur) actor).getGender().contentEquals("male")) {
                                    setPregnantCount(0);
                                    addCapability(BreedingState.Pregnant);
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() == 20) {
                            StegosaurEgg egg = new StegosaurEgg("egg", '0', false);
                            egg.addCapability(eggOf.Allosaur);
                            thisLocation.addItem(egg);
                            removeCapability(BreedingState.Pregnant);

                        }
                    }
                }
            }
            else{
                setBabyAge(getBabyAge()+1);
                if (getBabyAge()==50){
                    removeCapability(AgeGroup.Baby);
                }
            }
            setUnconsciousTurns(0);
            if(hitPoints>50 && !hasCapability(AgeGroup.Baby)){
                wander=new Following(false,true,false).getAction(this,map);
            }
            else if(hitPoints<90){
                System.out.println(this.name+"at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
                wander=new Following(false,true,true).getAction(this,map);;
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
                thisLocation.addItem(new AllosaurCorpse("Allosaur",'?'));
            }
        }



        return new DoNothingAction();
    }
}
