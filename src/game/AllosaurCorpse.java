package game;

public class AllosaurCorpse extends Corpse{
    private int duration = 20;
    public AllosaurCorpse(String name, char displayChar){
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
