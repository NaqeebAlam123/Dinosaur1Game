package game.dinosaurs.corpses;
import edu.monash.fit2099.engine.Item;

/**
 * Abstract class for implementing methods like getting time that corpse has been on ground and within every turn decrease it
 * @author Cheng Zi Ming
 * @see  Item
 * @version 1.0.0
 */
public abstract class Corpse extends Item{

    public Corpse(String name, char displayCharacter, boolean b){
        super(name,displayCharacter,false);
    }

    abstract void setCorpseDuration(int duration);

    public abstract int getCorpseDuration();

    abstract boolean checkDecayed();

    abstract boolean decreaseDuration();
}
