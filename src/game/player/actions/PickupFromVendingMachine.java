package game.player.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.dinosaurs.eggs.AllosaurEgg;
import game.dinosaurs.eggs.BrachiosaurEgg;
import game.dinosaurs.eggs.StegosaurEgg;
import game.player.Player;
import game.vending_machine.VendingMachine;
import game.vending_machine.other_items.Fruit;
import game.vending_machine.other_items.Laser;
import game.vending_machine.other_items.carnivoreMealKit;
import game.vending_machine.other_items.vegetarianMealKit;

/**
 * It allows the Player to add items from the Vending Machine to their inventory
 * @author Muhammad Naqeeb Alam
 * @see Fruit
 * @see Player
 * @see vegetarianMealKit
 * @see carnivoreMealKit
 * @see Laser
 * @see StegosaurEgg
 * @see BrachiosaurEgg
 * @see VendingMachine
 * @see PickUpItemAction
 * @version 1.0.0
 */
public class PickupFromVendingMachine extends PickUpItemAction {
    /**
     * Initialises the item to be picked from Vending Machine
     * @param item
     */
    public PickupFromVendingMachine(Item item) {
        super(item);
    }

    /**
     * This method is executed chooses to pick an item from Vending Machine
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a respond on item obtained from vending machine
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String str=super.execute(actor, map);
        Player player=(Player)actor;

        if (item instanceof Fruit){
            player.deductPoints(((Fruit)item).ecoPoints);
        }
        else if(item instanceof vegetarianMealKit){
            player.deductPoints(((vegetarianMealKit) item).ecoPoints);

        }
        else if(item instanceof carnivoreMealKit){
            player.deductPoints(((carnivoreMealKit) item).ecoPoints);

        }
        else if(item instanceof StegosaurEgg){
            player.deductPoints(((StegosaurEgg) item).ecoPoints);

        }
        else if(item instanceof BrachiosaurEgg){
            player.deductPoints(((BrachiosaurEgg) item).ecoPoints);

        }else if(item instanceof AllosaurEgg){
            player.deductPoints(((AllosaurEgg)item).ecoPoints);
        }
        else if(item instanceof Laser){
            player.deductPoints(((Laser) item).ecoPoints);

        }

        return  str + " from the Vending Machine";


    }
}
