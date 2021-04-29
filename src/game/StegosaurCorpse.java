package src.game;

public class StegosaurCorpse extends Corpse{
    private int duration = 20;
    public StegosaurCorpse(String name, char displayCharacter){
        super(name,displayCharacter,false);
    }

    /**
     * method to set the remaining duration of corpse before decay, default is 20
     * @param duration remaining duration of corpse
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


    public boolean decreaseDuration(){
        boolean valid = false;
        if (duration > 0){
        duration -= 1;
        valid = true;
        }
        return valid;
    }


}
