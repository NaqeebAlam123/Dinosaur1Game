package game;

public interface GameMode {
    public boolean checkGameFinished(Player player);
    public String getMessage();
}
