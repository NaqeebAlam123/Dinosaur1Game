package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Allosaur extends Dinosaur {
        // Will need to change this to a collection if Stegosaur gets additional Behaviours.


        /**
         * Constructor.
         * All Stegosaurs are represented by a 'd' and have 100 hit points.
         *
         * @param name the name of this Stegosaur
         */
        public Allosaur(String name,String gender) {
            super(name, 'd', 100, gender);

            hitPoints = 50;

        }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
