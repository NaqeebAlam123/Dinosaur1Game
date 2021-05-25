package game.player.game_modes;

import game.player.Player;
import game.player.exceptions.ModeExceptions;
import game.player.game_modes.GameMode;

public class Challenge implements GameMode {
    public boolean setMaxNumberOfMoves(int maxNumberOfMoves) {
        if (maxNumberOfMoves>0) {
            this.maxNumberOfMoves = maxNumberOfMoves;
            return true;
        }
        else {return false;}
    }

    public boolean setMaxNumberOfEcoPoints(int maxNumberOfEcoPoints) {
        if(maxNumberOfEcoPoints>0){
            this.maxNumberOfEcoPoints = maxNumberOfEcoPoints;
            return true;
        }
        else {return false;}

    }

    private int maxNumberOfMoves;
    private int maxNumberOfEcoPoints;
    private int currentNumberOfMoves=0;
    private String message;
    public Challenge(int maxNumberOfMoves,int maxNumberOfEcoPoints) throws ModeExceptions {
        if (!setMaxNumberOfEcoPoints(maxNumberOfEcoPoints)){
            throw new ModeExceptions("max number of eco points must be greater than 0 !");
        }
        if(!setMaxNumberOfMoves(maxNumberOfMoves)){
            throw new ModeExceptions("max number of moves must be greater than 0 !");
        }
    }

    @Override
    public boolean checkGameFinished(Player player) {
        if(player.getEcoPoints()>=maxNumberOfEcoPoints){
            if(currentNumberOfMoves<=maxNumberOfMoves){
                message="Congratulations You have won";
            }
            else{
                message="You lost";
            }

            return true;
        }
        else {
            currentNumberOfMoves=currentNumberOfMoves+1;
            return false;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
