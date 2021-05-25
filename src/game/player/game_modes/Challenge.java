package game.player.game_modes;

import game.player.Player;
import game.player.exceptions.ModeExceptions;
import game.player.game_modes.GameMode;

/**
 * This class provides computation in regards to Challenge game mode
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see GameMode
 * @see game.Application
 */
public class Challenge implements GameMode {
    /**
     * set max number of moves in which player needs to complete the game
     * @param maxNumberOfMoves
     * @return
     */
    public boolean setMaxNumberOfMoves(int maxNumberOfMoves) {
        if (maxNumberOfMoves>0) {
            this.maxNumberOfMoves = maxNumberOfMoves;
            return true;
        }
        else {return false;}
    }

    /**
     * set max number of eco points which player needs to achieve to complete the game
     * @param maxNumberOfEcoPoints
     * @return
     */
    public boolean setMaxNumberOfEcoPoints(int maxNumberOfEcoPoints) {
        if(maxNumberOfEcoPoints>0){
            this.maxNumberOfEcoPoints = maxNumberOfEcoPoints;
            return true;
        }
        else {return false;}

    }

    /**
     * maximum number of moves
     */
    private int maxNumberOfMoves;
    /**
     * maximum number of eco points
     */
    private int maxNumberOfEcoPoints;
    /**
     * current number of moves
     */
    private int currentNumberOfMoves=0;
    /**
     * message to be displayed
     */
    private String message;

    /**
     * constructor for initialising the max number of moves and eco points and if it is not setted by setters,then raise an exception
     * @param maxNumberOfMoves
     * @param maxNumberOfEcoPoints
     * @throws ModeExceptions
     */
    public Challenge(int maxNumberOfMoves,int maxNumberOfEcoPoints) throws ModeExceptions {
        if (!setMaxNumberOfEcoPoints(maxNumberOfEcoPoints)){
            throw new ModeExceptions("max number of eco points must be greater than 0 !");
        }
        if(!setMaxNumberOfMoves(maxNumberOfMoves)){
            throw new ModeExceptions("max number of moves must be greater than 0 !");
        }
    }

    /**
     * checks if game is finished
     * @param player
     * @return
     */
    @Override
    public boolean checkGameFinished(Player player) {
        /* compares player current eco points to the maximum and if they are equal or got greater than that*/
        if(player.getEcoPoints()>=maxNumberOfEcoPoints){
            //check if the game is done in sufficient number of moves and modify the message to have winning or losing statement
            if(currentNumberOfMoves<=maxNumberOfMoves){
                message="Congratulations You have won";
            }
            else{
                message="You lost";
            }

            return true;
        }
        else {
            //false return indicates game is not finished and increment the current number of moves
            currentNumberOfMoves=currentNumberOfMoves+1;
            return false;
        }
    }

    /**
     * gets message
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }
}
