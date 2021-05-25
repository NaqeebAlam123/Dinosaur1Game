package game.vending_machine;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.vending_machine.other_items.Fruit;
import game.vending_machine.other_items.Laser;
import game.vending_machine.other_items.carnivoreMealKit;
import game.dinosaurs.eggs.AllosaurEgg;
import game.dinosaurs.eggs.BrachiosaurEgg;
import game.dinosaurs.eggs.PterodactylsEgg;
import game.dinosaurs.eggs.StegosaurEgg;
import game.player.Player;
import game.player.actions.PickupFromVendingMachine;
import game.vending_machine.other_items.vegetarianMealKit;

/**
 * Class that encompasses all the items that Vending machine can hold
 * @author Muhammad Naqeeb Alam
 * @see Fruit
 * @see game.vending_machine.other_items.vegetarianMealKit
 * @see game.vending_machine.other_items.carnivoreMealKit
 * @see Laser
 * @see StegosaurEgg
 * @see BrachiosaurEgg
 * @see AllosaurEgg
 * @see Player
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class VendingMachine extends Ground {
    /**
     * fruit object
     */
    private Fruit fruit=new Fruit("apple",'a',true);
    /**
     * vegetarianMealKit object
     */
    private vegetarianMealKit vegetarianMealKit=new vegetarianMealKit();
    /**
     * carnivoreMealKit object
     */
    private carnivoreMealKit carnivoreMealKit=new carnivoreMealKit();
    /**
     * laser object
     */
    private Laser laser=new Laser();
    /**
     * StegosaurEgg object
     */
    private StegosaurEgg stegosaurEgg=new StegosaurEgg("stegosaurEgg",'0',true);
    /**
     * BrachiosaurEgg object
     */
    private BrachiosaurEgg brachiosaurEgg=new BrachiosaurEgg("brachiosaurEgg",'0',true);
    /**
     * AllosaurEgg object
     */
    private AllosaurEgg allosaurEgg = new AllosaurEgg("AllosaurEgg",'0',true);

    private PterodactylsEgg pterodactylsEgg = new PterodactylsEgg("PterodactylsEgg",'0',true);
    /**
     * Returns the actions that can be done on Vending Machine
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions= super.allowableActions(actor, location, direction);
        if (actor instanceof Player){
            Player player=(Player) actor;
            int points=player.getEcoPoints();
            System.out.println("Eco Points : "+points);
            if (points> fruit.ecoPoints){
                actions.add(new PickupFromVendingMachine(fruit));
            }
            if(points> vegetarianMealKit.ecoPoints){
                actions.add(new PickupFromVendingMachine(vegetarianMealKit));
            }
            if(points> carnivoreMealKit.ecoPoints) {
                actions.add(new PickupFromVendingMachine(carnivoreMealKit));
            }
            if(points>laser.ecoPoints){
                actions.add(new PickupFromVendingMachine(laser));
            }
            if(points>stegosaurEgg.ecoPoints){
                actions.add(new PickupFromVendingMachine(stegosaurEgg));
            }
            if(points> brachiosaurEgg.ecoPoints){
                actions.add(new PickupFromVendingMachine(brachiosaurEgg));
            }
            if(points> allosaurEgg.ecoPoints){
                actions.add(new PickupFromVendingMachine(allosaurEgg));
            }
            if(points> pterodactylsEgg.ecoPoints){
                actions.add(new PickupFromVendingMachine(allosaurEgg));
            }
        }
     return actions;
    }

    public VendingMachine() {
        super('V');
    }
}
