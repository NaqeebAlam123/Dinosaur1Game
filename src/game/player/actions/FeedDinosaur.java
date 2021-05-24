package game.player.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.vending_machine.other_items.Fruit;
import game.vending_machine.other_items.carnivoreMealKit;
import game.dinosaurs.live.Allosaur;
import game.dinosaurs.live.Brachiosaur;
import game.dinosaurs.live.Dinosaur;
import game.dinosaurs.live.Stegosaur;
import game.player.Player;
import game.vending_machine.other_items.vegetarianMealKit;

/**
 * Class for feeding dinosaur
 * @author Muhammad Naqeeb Alam
 * @see Stegosaur
 * @see Brachiosaur
 * @see Allosaur
 * @see Actor
 * @see Fruit
 * @see vegetarianMealKit
 * @see carnivoreMealKit
 * @version 1.0.0
 */
public class FeedDinosaur extends Action {
    /**
     * Dinosaur
     */
    protected Actor target;

    /**
     * Intiallises the target which is dinosaur
     * @param target
     */
    public FeedDinosaur(Actor target) {
        this.target = target;
    }

    /**
     * This method is executed when player chose to feed the dinosaur
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor , GameMap map){
      for(Item item :actor.getInventory()){
          if (item instanceof Fruit){
              target.heal(20);
              Player player=(Player) actor;
              player.incrementPoints(10);
              actor.removeItemFromInventory(item);
              break;
          }
          else if(item instanceof vegetarianMealKit && (target instanceof Stegosaur||target instanceof Brachiosaur)){
              Dinosaur dinosaur=(Dinosaur) target;
              dinosaur.heal(dinosaur.getMaxHitPoints()-dinosaur.getHitPoints());
              actor.removeItemFromInventory(item);
              break;

          }
          else if(item instanceof carnivoreMealKit && (target instanceof Allosaur)){
              Dinosaur dinosaur=(Dinosaur) target;
              dinosaur.heal(dinosaur.getMaxHitPoints()-dinosaur.getHitPoints());
              actor.removeItemFromInventory(item);
              break;
          }

      }


      return menuDescription(actor);
    }
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}
