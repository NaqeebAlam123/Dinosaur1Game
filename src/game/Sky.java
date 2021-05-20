package game;

import java.util.Random;

public class Sky {
    private static boolean raining=false;
    private static int rainTurns=1;
    public static boolean isRaining() {
        return raining;
    }

    public static void setRaining(boolean raining) {
        Sky.raining = raining;
    }

    public static int getRainTurns() {
        return rainTurns;
    }

    public static void setRainTurns(int rainTurns) {
        Sky.rainTurns = rainTurns;
    }
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
