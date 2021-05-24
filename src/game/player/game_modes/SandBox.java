package game.player.game_modes;

import game.player.Player;
import game.player.game_modes.GameMode;

public class SandBox implements GameMode {
    @Override
    public boolean checkGameFinished(Player player) {
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
