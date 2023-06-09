package game.dinosaurs.corpses;

import game.dinosaurs.live.Brachiosaur;

/**
 * corpse for brachiosaur
 * @author Cheng Zi Ming
 * @see Corpse
 * @see Brachiosaur
 * @version 1.0.0
 */

public class BrachiosaurCorpse extends Corpse{
    private int duration = 40;

    public BrachiosaurCorpse(String name, char displayChar){
        super(name,displayChar,false);
    }
    public void setCorpseDuration(int duration ){
        this.duration = duration;
    }

    /**
     *  method to get the remaining duration
     * @return remaining duration of corpse before decay
     */
    public int getCorpseDuration(){
        return duration;
    }

    /**
     * A method that constantly checks and determine decayed corpse
     * @return the status of the corpse whether it is decayed(true/false)
     */
    public boolean checkDecayed(){
        boolean decayed = false;
        if(duration <= 0){
            decayed = true;
        }
        return decayed;
    }


    public boolean decreaseDuration(){
        boolean valid = false;
        if (duration > 0){
            duration -= 1;
            valid = true;
        }
        return valid;
    }

}
