package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * @author Muhammad Naqeeb Alam
 * @see WeaponItem
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
