package game.dinosaurs.corpses;

import game.dinosaurs.live.Allosaur;

/**
 * corpse for Allosaur
 * @author Cheng Zi Ming
 * @see Corpse
 * @see Allosaur
 * @version 1.0.0
 */
public class AllosaurCorpse extends Corpse{
    private int duration = 20;

    /**
     * constructor for Allosaur Corpse
     * @param name
     * @param displayChar
     */
    public AllosaurCorpse(String name, char displayChar){
        super(name,displayChar,false);
    }

    /**
     * Set decay duration of the corpse
     * @param duration
     */
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


    /**
     * decrease the duration of the corpse
     * @return a boolean variable on if the duration is decreased
     */
    public boolean decreaseDuration(){
        boolean valid = false;
        if (duration > 0){
            duration -= 1;
            valid = true;
        }
        return valid;
    }

}
