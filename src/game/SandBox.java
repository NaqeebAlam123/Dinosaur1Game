package game;

public class SandBox implements GameMode{
    @Override
    public boolean checkGameFinished(Player player) {
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
