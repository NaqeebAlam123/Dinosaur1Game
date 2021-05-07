package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * Egg super class used by dinosaur of different types
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Item
 * @see Location
 * @see Player
 * @see Dinosaur
 * @see Util
 */
public class Egg  extends Item {
    /**
     * stores the age of egg
     */
    private int eggAge;

    /**
     * Intiallises name, display Character, portable value
     * @param name
     * @param displayChar
     * @param portable
     */
    public Egg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        eggAge=0;
    }

    /**
     * This method is invoked when ground at which GameMap is traversing is a Tree
     * Hatches egg after it reaches certain age and add dinosaur to the location
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        String gender;
        eggAge=eggAge+1;
        GameMap map=currentLocation.map();
        Player player=Util.findPlayer(map);
        Random r= new Random();
        if (r.nextBoolean()){
            gender="male";
        }
        else{
            gender="female";
        }

        if (hasCapability(eggOf.Stegosaur)&& eggAge==30){
            if (currentLocation.getActor()==null) {
                creatingBabyDinosaur(player, 100, currentLocation, new Stegosaur("Stegosaur", gender));
            }
            else{
                eggAge=eggAge-1;
            }

        }
        else if (hasCapability(eggOf.Brachiosaur)&& eggAge==10){
            if (currentLocation.getActor()==null) {
                creatingBabyDinosaur(player, 1000, currentLocation, new Brachiosaur("Brachiosaur", gender));
            }
            else{
                eggAge=eggAge-1;
            }
        }
        else if (hasCapability(eggOf.Allosaur)&& eggAge==50){
            if (currentLocation.getActor()==null) {
                creatingBabyDinosaur(player, 1000, currentLocation, new Allosaur("Brachiosaur", gender));
            }
            else {
                eggAge=eggAge-1;
            }
        }



    }

    /**
     * Incrementing player points and adding dinosaur to location upon hatching of egg
     * @param player
     * @param points
     * @param currentLocation
     * @param dinosaur
     */
    private void creatingBabyDinosaur(Player player,int points,Location currentLocation,Dinosaur dinosaur){
            System.out.println("Egg hatches at ("+currentLocation.x()+","+currentLocation.y()+")");

            player.incrementPoints(points);
            currentLocation.removeItem(this);

            dinosaur.addCapability(AgeGroup.Baby);
            dinosaur.setHitPoints(10);
            dinosaur.setBabyAge(0);
            currentLocation.addActor(dinosaur);

        }
    }

