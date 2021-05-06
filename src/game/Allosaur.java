package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
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
            super(name, 'A', 100, gender);

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
        int x = thisLocation.x();
        int y = thisLocation.y();
        Actor back = null;
        Actor bottom = null;
        Actor front = map.at(x + 1, y).getActor();
        if(x> 1){
        back = map.at(x - 1, y).getActor();}
        Actor top = map.at(x , y + 1).getActor();
        if(y>1){
        bottom = map.at(x, y - 1).getActor();}
        Action wander = null;
        if(isConscious()){
            boolean corpseExist = false;
            Corpse corpse = null;
            Egg egg = null;
            List<Item> item = thisLocation.getItems();
            for(int i =0;i<item.size();i++){
                if(item.get(i) instanceof Corpse){
                    corpse = (Corpse) item.get(i);
                    corpseExist = true;
                }else if(item.get(i) instanceof  Egg){
                    egg = (Egg) item.get(i);
                }
            }
            if(corpseExist){ //if it is a corpse
                if (corpse instanceof AllosaurCorpse || corpse instanceof StegosaurCorpse){
                    System.out.println("Allosaur found a corpse and ate it. Heal 50");
                    if(this.getMaxHitPoints() > (this.getHitPoints() + 50)){
                    this.heal(50);
                    }else{
                        this.setHitPoints(this.getMaxHitPoints());
                    }
                    thisLocation.removeItem(corpse);
                }else if(corpse instanceof BrachiosaurCorpse){
                    int heal = this.getMaxHitPoints()-this.getHitPoints();
                    System.out.println("Allosaur found a Brachiosaur corpse and ate it. Heal to full hp");
                    this.heal(heal);
                    thisLocation.removeItem(corpse);
                }
            }else if(thisLocation.getItems() instanceof Egg){
                System.out.println("Allosaur found an egg and ate it. Heal 10hp");
                this.heal(10);
                thisLocation.removeItem(egg);
            }else if(top instanceof Stegosaur|| bottom instanceof Stegosaur
                    || front instanceof Stegosaur|| back instanceof  Stegosaur){
                Stegosaur stegosaur = null;
                    if(top instanceof  Stegosaur){
                        stegosaur = (Stegosaur) top;
                    }else if(bottom instanceof Stegosaur){
                        stegosaur = (Stegosaur) bottom;
                }else if(front instanceof Stegosaur){
                        stegosaur = (Stegosaur) front;
                    }
                    if (back instanceof  Stegosaur){
                        stegosaur = (Stegosaur) back;
                    }
                    if (hasCapability(AgeGroup.Baby)){ // Its a baby
                        if(!stegosaur.getHurt()){ // If stegosaur is not hurt
                            System.out.println("Baby Allosaur at ("+ thisLocation.x() + ", " + thisLocation.y() + ") attacks Stegosaur. Heal 10hp");
                            this.heal(10);
                            stegosaur.hurt(10); //attack stegosaur
                            stegosaur.setHurt(true);
                            if(stegosaur.getHitPoints() <= 0){
                                if(this.getMaxHitPoints() > (this.getHitPoints() + 50)){
                                    this.heal(50);
                                }else{
                                    this.setHitPoints(this.getMaxHitPoints());
                                }
                                ActorLocations actorLocations = new ActorLocations();
                                actorLocations.remove(stegosaur); //remove dead stegosaur
                            }
                        }
                    }else{
                    if(!stegosaur.getHurt()){ // If stegosaur is not hurt
                        System.out.println("Allosaur  at ( "+ thisLocation.x() + ", " + thisLocation.y() + ") attacks Stegosaur. Heals 20hp");
                        this.heal(20);
                        stegosaur.hurt(20); //attack stegosaur
                        stegosaur.setHurt(true);
                        if(stegosaur.getHitPoints() <= 0){
                            System.out.println("Stegosaur is dead. Continue to feed on corpse");
                            if(this.getMaxHitPoints() > (this.getHitPoints() + 50)){
                                this.heal(50);
                            }else{
                                this.setHitPoints(this.getMaxHitPoints());
                            }
                            ActorLocations actorLocations = new ActorLocations();
                            actorLocations.remove(stegosaur); //remove dead stegosaur
                        }
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
                                    this.setBreedingState(false); //back to false after success breeding
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() == 20) {
                            AllosaurEgg EGG = new AllosaurEgg("egg", '0', false);
                            EGG.addCapability(eggOf.Allosaur);
                            thisLocation.addItem(EGG);
                            removeCapability(BreedingState.Pregnant);

                        }
                    }
                }
            }
            else{
                setBabyAge(getBabyAge()+1);
                if (getBabyAge()==50){
                    removeCapability(AgeGroup.Baby);
                    setHitPoints(50);
                }
            }
            setUnconsciousTurns(0);
            if(hitPoints>50 && hitPoints < 70 && !hasCapability(AgeGroup.Baby)){
                int breed = r.nextInt(100)+1;
                if(breed < 70 && !this.getBreedingState()) {
                    this.setBreedingState(true);
                    wander = new Following(false, true, false, false).getAction(this, map);
                }
            }
            else if(hitPoints<90){

                System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
                if (hitPoints < 70){
                    // Very Hungry
                wander=new Following(false,false,true,false).getAction(this,map);
                }else{
                    // Not so hungry
                    wander=new Following(false,false,false,true).getAction(this,map);
                }
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
