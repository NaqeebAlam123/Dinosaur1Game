package game.player.game_modes;

import game.Application;
import game.player.Player;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Player
 * @see Challenge
 * @see SandBox
 * @see Application
 * this class consists of methods that will be used for chosen game mode by the player
 */
public interface GameMode {
    /**
     * Checks if game has finished
     * @param player
     * @return
     */
    public boolean checkGameFinished(Player player);

    /**
     * gets the message for the choosen game mode
     * @return
     */
    public String getMessage();
}
