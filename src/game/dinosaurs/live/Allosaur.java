package game.dinosaurs.live;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.behaviours.Following;
import game.dinosaurs.corpses.*;
import game.dinosaurs.eggs.AllosaurEgg;
import game.dinosaurs.eggs.Egg;
import game.dinosaurs.functions.DinosaurFunctionsClass;
import game.dinosaurs.status.AgeGroup;
import game.dinosaurs.status.BreedingState;
import game.dinosaurs.status.FlyBehaviour;
import game.dinosaurs.status.eggOf;
import game.grounds.Lake;
import game.static_classes.Sky;

import java.util.List;
import java.util.Random;

/**
 * A carnivorous Dinosaur - Allosaur
 * @author Cheng Zi Ming
 * @see Dinosaur
 * @see Stegosaur
 * @see Brachiosaur
 * @see StegosaurCorpse
 * @see BrachiosaurCorpse
 * @see AllosaurCorpse
 * @see Following
 * @see BreedingState
 * @see AgeGroup
 * @see Location
 * @version 1.0.0
 */
public class Allosaur extends Dinosaur {
        // Will need to change this to a collection if Stegosaur gets additional Behaviours.
        private DinosaurFunctionsClass dinosaurFunctionsClass;

        /**
         * Constructor.
         * All Allosaur are represented by a 'd' and have 100 hit points.
         *
         * @param name the name of this Allosaur
         */
        public Allosaur(String name,String gender,DinosaurFunctionsClass dinosaurFunctionsClass) {
            super(name, 'A', 100,60, 100, gender);
            this.dinosaurFunctionsClass=dinosaurFunctionsClass;
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

        Action wander = null;

        if(isConscious()){
            dinosaurFunctionsClass.drink(this,thisLocation);
            setUnconsciousTurns(0);
            boolean corpseExist = false;
            boolean eggExist = false;
            Corpse corpse = null;
            Egg egg = null;

            List<Item> item = thisLocation.getItems();
            //loop through the list of items on the ground
            for (Item value : item) {
                if (value instanceof Corpse) {
                    corpse = (Corpse) value;
                    corpseExist = true;
                } else if (value instanceof Egg) {
                    egg = (Egg) value;
                    eggExist = true;
                }
            }


            if(corpseExist){ //if it is a corpse
                if (corpse instanceof AllosaurCorpse || corpse instanceof StegosaurCorpse){
                    System.out.println("Allosaur found a corpse and ate it. Heal 50hp");
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
                }else if (corpse instanceof PterodactylsCorpse){
                    System.out.println("Allosaur found a Pterodactyls corpse and ate it. Heal 50hp");
                    if(this.getMaxHitPoints() > (this.getHitPoints() + 30)){
                        this.heal(30);
                    }else{
                        this.setHitPoints(this.getMaxHitPoints());
                    }
                }
            }else if(eggExist){ // if it is an egg
                System.out.println("Allosaur at (" + x + ", " + y + ") found an egg and ate it. Heal 10hp");
                this.heal(10);
                thisLocation.removeItem(egg);
            }

            for (Exit exit : thisLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getActor() instanceof Stegosaur) {
                    Stegosaur stegosaur = (Stegosaur) destination.getActor();
                    if (hasCapability(AgeGroup.Baby)) { // Its a baby
                        if (!stegosaur.getHurt()) { // If stegosaur is not hurt
                            System.out.println("Baby Allosaur at (" + x + ", " + y + ") attacks Stegosaur. Heal 10hp");
                            this.heal(10);
                            stegosaur.hurt(10); //attack stegosaur
                            stegosaur.setHurt(true);
                            if (stegosaur.getHitPoints() <= 0) {
                                if (this.getMaxHitPoints() > (this.getHitPoints() + 50)) {
                                    this.heal(50);
                                } else {
                                    this.setHitPoints(this.getMaxHitPoints());
                                }
                                ActorLocations actorLocations = new ActorLocations();
                                actorLocations.remove(stegosaur); //remove dead stegosaur
                            }
                        }
                    } else {
                        if (!stegosaur.getHurt()) { // If stegosaur is not hurt
                            System.out.println("Allosaur  at ( " + thisLocation.x() + ", " + thisLocation.y() + ") attacks Stegosaur. Heals 20hp");
                            this.heal(20);
                            stegosaur.hurt(20); //attack stegosaur
                            stegosaur.setHurt(true);
                            if (stegosaur.getHitPoints() <= 0) {
                                System.out.println("Stegosaur is dead. Continue to feed on corpse");
                                if (this.getMaxHitPoints() > (this.getHitPoints() + 50)) {
                                    this.heal(50);
                                } else {
                                    this.setHitPoints(this.getMaxHitPoints());
                                }

                                map.removeActor(stegosaur); //remove dead stegosaur
                            }
                        }
                    }
                    break;
                }
            }


            for (Exit exit : thisLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getActor() instanceof Pterodactyls) {
                    Pterodactyls pterodactyls = (Pterodactyls) destination.getActor();
                    if (!pterodactyls.hasCapability(FlyBehaviour.FLY)) { // If pterodactyl is not flying
                        System.out.println("Allosaur  at ( " + thisLocation.x() + ", " + thisLocation.y() + ") found a walking Pterodactyl and ate it.");
                        this.setHitPoints(getMaxHitPoints());
                        map.removeActor(pterodactyls);
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
                                    System.out.println("Allosaur at (" + x + ", " + y + ") is pregnant.");
                                    setPregnantCount(0);
                                    addCapability(BreedingState.Pregnant);
                                    ((Allosaur)actor).setBreedingState(false); //back to false after success breeding
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() == 20) {
                            System.out.println("Allosaur at (" + x + ", " + y + ") lays an egg.");
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
                    System.out.println("Baby Allosaur at (" + x + ", " + y + ") grows into an adult.");
                    removeCapability(AgeGroup.Baby);
                    setHitPoints(50);
                }
            }
            setUnconsciousTurns(0);
            if(hitPoints>50 && hitPoints < 70 && !hasCapability(AgeGroup.Baby)){
                int breed = r.nextInt(100)+1;
                if(breed < 70 && !this.getBreedingState()) {
                    System.out.println("Allosaur at (" + x + ", " + y + ") wants to breed.");
                    this.setBreedingState(true);
                    wander = new Following(false, true, false, false, false, false).getAction(this, map);
                }
            }
            else if(hitPoints<90){

                System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting hungry!");
                if (hitPoints < 70){
                    // Very Hungry
                wander=new Following(false,false,true,false,false, false).getAction(this,map);
                }else{
                    // Not so hungry
                    wander=new Following(false,false,false,true,false, false).getAction(this,map);
                }
            }else if(getWaterLevel()<50){

                System.out.println(this.name+" at ("+thisLocation.x()+","+thisLocation.y()+") is getting thirsty!");

                wander=new Following(false,false,false,false,true, false).getAction(this,map);
            }
            else{
                wander=getBehaviour().getAction(this,map);
            }

            //decrease water level and food level by 1
            setWaterLevel(getWaterLevel()-1);
            hurt(1);

            if (wander!=null){
                return wander;
            }
        }
        else{
            System.out.println("Allosaur at (" + thisLocation.x() + ", " +thisLocation.y() + ") is unconscious.");
            if(Sky.isRaining()){
                setWaterLevel(10);
            }
            if(!isConscious()) {
                incrementUnconsciousTurns();
                if (getUnconsciousTurns() == 20) {
                    System.out.println("Allosaur at (" + thisLocation.x() + ", " + thisLocation.y() + ") is dead");
                    map.removeActor(this);
                    thisLocation.addItem(new AllosaurCorpse("Allosaur", '?'));
                }
            }
        }



        return new DoNothingAction();
    }
}
