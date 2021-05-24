package game;

import java.util.Random;

/**
 * @author Muhammad Naqeeb Alam
 * @version 1.0.0
 * @see Lake
 * @see Stegosaur
 * @see Brachiosaur
 * @see Allosaur
 * @see Pterodactyls
 * @see Player
 * This class is used to keep a check if sky will rain for specific turn or not
 */
public class Sky {
    /**
     * boolean value indicating if it is raining
     */
    private static boolean raining=false;
    /**
     * It is the number of turns that have passed since the last rain or beginning of game
     */
    private static int rainTurns=1;

    /**
     * returns the raining attribute value
     * @return
     */
    public static boolean isRaining() {
        return raining;
    }

    /**
     * set the raining attribute value
     * @param raining
     */
    public static void setRaining(boolean raining) {
        Sky.raining = raining;
    }

    /**
     * get rainTurns attribute value
     * @return
     */
    public static int getRainTurns() {
        return rainTurns;
    }

    /**
     * set rainTurns attribute value
     * @param rainTurns
     */
    public static void setRainTurns(int rainTurns) {
        Sky.rainTurns = rainTurns;
    }

    /**
     * computes if sky will rain for specific turn
     */
    public static void process(){
        Random r=new Random();
        if (Sky.getRainTurns()==10){
            if(r.nextInt(100)+1>80){
                Sky.setRaining(true);
            }
            else{
                Sky.setRaining(false);
            }

            Sky.setRainTurns(1);
        }
        else{
            Sky.setRaining(false);
            Sky.setRainTurns(Sky.getRainTurns()+1);
        }

    }




}
