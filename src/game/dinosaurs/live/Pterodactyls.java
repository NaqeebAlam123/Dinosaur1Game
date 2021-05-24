package game.dinosaurs.live;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.behaviours.Following;
import game.dinosaurs.corpses.Corpse;
import game.dinosaurs.corpses.PterodactylsCorpse;
import game.dinosaurs.eggs.PterodactylsEgg;
import game.dinosaurs.functions.DinosaurFunctionsClass;
import game.dinosaurs.status.AgeGroup;
import game.dinosaurs.status.BreedingState;
import game.dinosaurs.status.FlyBehaviour;
import game.dinosaurs.status.eggOf;
import game.grounds.Tree;
import game.static_classes.Sky;

import java.util.List;
import java.util.Random;

public class Pterodactyls extends Dinosaur {
    private DinosaurFunctionsClass dinosaurFunctionsClass;
    private DinosaurFunctionsClass dinosaurFunctionsClass2;
    public Pterodactyls(String name,String gender,DinosaurFunctionsClass dinosaurFunctionsClass,DinosaurFunctionsClass dinosaurFunctionsClass2) {
        super(name, 'P', 100,60, 100, gender);
        this.dinosaurFunctionsClass=dinosaurFunctionsClass;
        this.dinosaurFunctionsClass2=dinosaurFunctionsClass2;
        hitPoints = 50;
        addCapability(FlyBehaviour.FLY);
    }


    private int flyDuration = 30;



    public void setFlyDuration(int flyDuration){
        this.flyDuration = flyDuration;
    }

    public int getFlyDuration(){
        return flyDuration;
    }

    public void decFlyDuration(){
        flyDuration -= 1;
    }

    /**
     *it handles the actions made by Pterodactyls at each turn
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Random r = new Random();
        Location thisLocation = map.locationOf(this);
        int x = thisLocation.x();
        int y = thisLocation.y();


        Action wander = null;

        if (isConscious()) {
            setUnconsciousTurns(0);
            boolean corpseExist = false;
            Corpse corpse = null;

            List<Item> item = thisLocation.getItems();
            for (Item value : item) {
                if (value instanceof Corpse) {
                    corpse = (Corpse) value;
                    corpseExist = true;
                }
            }


            if (thisLocation.getGround() instanceof Tree){
                setFlyDuration(30);
            }
            if(!hasCapability(FlyBehaviour.FLY)) {
                dinosaurFunctionsClass.drink(this, thisLocation);
            }
            else {
                dinosaurFunctionsClass2.catchFish(this, thisLocation);
            }

            /*if (drink) {
                addWaterLevel(30);
                lake.decNumberOfSips();
            }*/

            if (corpseExist) { //if it is a corpse

                System.out.println("Pterodactyls found a corpse and ate it. Heal 10hp");
                if (this.getMaxHitPoints() > (this.getHitPoints() + 10)) {
                        this.heal(10);
                } else {
                        this.setHitPoints(this.getMaxHitPoints());
                }
                if (corpse.getCorpseDuration() <= 0) {
                    thisLocation.removeItem(corpse);
                }

            }

            if (getFlyDuration() <= 0){
                removeCapability(FlyBehaviour.FLY);
            }
            }
            if (!hasCapability(AgeGroup.Baby)) {
                if (getGender().contentEquals("female")) {
                    if (!(hasCapability(BreedingState.Pregnant))) {
                        for (Exit exit : thisLocation.getExits()) {
                            Location destination = exit.getDestination();
                            if (destination.containsAnActor()) {
                                Actor actor = destination.getActor();
                                if (actor instanceof Allosaur && ((Allosaur) actor).getGender().contentEquals("male") && thisLocation.getGround() instanceof Tree) {
                                    System.out.println("Pterodactyl at (" + x + ", " + y + ") is pregnant.");
                                    setPregnantCount(0);
                                    addCapability(BreedingState.Pregnant);
                                    ((Allosaur) actor).setBreedingState(false); //back to false after success breeding
                                    break;
                                }

                            }
                        }
                    } else {
                        incrementPregnantCount();
                        if (getPregnantCount() >= 20 && thisLocation.getGround() instanceof Tree) {
                            System.out.println("Pterodactyl at (" + x + ", " + y + ") lays an egg.");
                            PterodactylsEgg EGG = new PterodactylsEgg("egg", '0', false);
                            EGG.addCapability(eggOf.Pterodactyls);
                            thisLocation.addItem(EGG);
                            removeCapability(BreedingState.Pregnant);

                        }
                    }
                }
            } else {
                setBabyAge(getBabyAge() + 1);
                if (getBabyAge() == 50) {
                    System.out.println("Baby Pterodactyl at (" + x + ", " + y + ") grows into an adult.");
                    removeCapability(AgeGroup.Baby);
                    setHitPoints(50);
                }
            }
            setUnconsciousTurns(0);
            if(getPregnantCount() >= 15) {
                wander = new Following(false, false, false, false, false,true).getAction(this, map);


            }else if (hitPoints > 50 && hitPoints < 70 && !hasCapability(AgeGroup.Baby)) {
                int breed = r.nextInt(100) + 1;
                if (breed < 70 && !this.getBreedingState()) {
                    System.out.println("Pterodactyl at (" + x + ", " + y + ") wants to breed.");
                    this.setBreedingState(true);
                    wander = new Following(false, true, false, false, false,false).getAction(this, map);
                }
            } else if (hitPoints < 90) {

                System.out.println(this.name + " at (" + thisLocation.x() + "," + thisLocation.y() + ") is getting hungry!");
                if (hitPoints < 70) {
                    // Very Hungry
                    wander = new Following(false, false, true, false, false,false).getAction(this, map);
                }
            } else if (getWaterLevel() < 50) {

                System.out.println(this.name + " at (" + thisLocation.x() + "," + thisLocation.y() + ") is getting thirsty!");
                wander=new Following(false,false,false,false,true,false).getAction(this,map);
            } else {
                wander = getBehaviour().getAction(this, map);
            }

            //decrease water level and food level by 1
            hurt(1);
            setWaterLevel(getWaterLevel()-1);
            if(getFlyDuration() - 1 > 0) {
                decFlyDuration();
            }
            if (wander != null) {
                return wander;
            }
         else {
             System.out.println("Pterodactyls at (" + thisLocation.x() + ", " +thisLocation.y() + ") is unconscious.");
            incrementUnconsciousTurns();
            if (Sky.isRaining()){
                setWaterLevel(10);
            }
            if(!isConscious()) {
                if (getUnconsciousTurns() == 20) {
                    System.out.println("Pterodactyl at (" + thisLocation.x() + ", " + thisLocation.y() + ") is dead");
                    map.removeActor(this);
                    thisLocation.addItem(new PterodactylsCorpse("Pterodactyl", '?'));
                }
            }
        }


        return new DoNothingAction();
    }
}
