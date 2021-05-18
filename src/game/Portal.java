package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

public class Portal extends Item {
    public Portal(){
        super("Portal",'=',false);
    }
    public void addAction(Action action) {
        this.allowableActions.add(action);
    }
}
