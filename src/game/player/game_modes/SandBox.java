package game.player.game_modes;

import game.player.Player;
import game.player.game_modes.GameMode;

/**
 * This class provides computation in regards to Sandbox game mode
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see GameMode
 * @see game.Application
 */
public class SandBox implements GameMode {
    /**
     * checks if game is finished
     * @param player
     * @return
     */
    @Override
    public boolean checkGameFinished(Player player) {
        //returns false as sandbox will proceed normally unitl player dies
        return false;
    }

    /**
     * gets message
     * @return
     */
    @Override
    public String getMessage() {
        return null;
    }
}
