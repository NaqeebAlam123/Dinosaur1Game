package game.vending_machine.other_items;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.player.actions.PickupFromVendingMachine;
import game.vending_machine.VendingMachine;

/**
 * It is used by Player to kill dinosaurs
 * @author Muhammad Naqeeb Alam
 * @see WeaponItem
 * @see Item
 * @see VendingMachine
 * @see PickupFromVendingMachine
 * @version 1.0.0
 */
public class Laser extends WeaponItem {
    /**
     * a final value which is the points require to purchase Laser in Vending Machine
     */
    public final int ecoPoints=500;
    public Laser() {
        super("Laser", '/', 50, "electrocutes");
    }

}
