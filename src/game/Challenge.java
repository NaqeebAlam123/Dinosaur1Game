package game;

public class Challenge implements GameMode{
    private int maxNumberOfMoves;
    private int maxNumberOfEcoPoints;
    private int currentNumberOfMoves=0;
    private String message;
    public Challenge(int maxNumberOfMoves,int maxNumberOfEcoPoints) {
        this.maxNumberOfEcoPoints=maxNumberOfEcoPoints;
        this.maxNumberOfMoves=maxNumberOfMoves;
    }

    @Override
    public boolean checkGameFinished(Player player) {
        if(player.getEcoPoints()==maxNumberOfEcoPoints){
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
