package game;
import edu.monash.fit2099.engine.Item;

public abstract class Corpse extends Item{

    public Corpse(String name, char displayCharacter, boolean b){
        super(name,displayCharacter,false);
    }

    abstract void setCorpseDuration(int duration);

    abstract int getCorpseDuration();

    abstract boolean checkDecayed();

    abstract boolean decreaseDuration();
}
