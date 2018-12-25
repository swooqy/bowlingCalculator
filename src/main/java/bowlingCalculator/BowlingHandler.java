package bowlingCalculator;

import java.util.ArrayList;

public class BowlingHandler {
    ArrayList<ScoreHandler> playerStates;
    int playerCount;
    BowlingHandler(int playerCount){
        playerStates=new ArrayList<ScoreHandler>();
        this.playerCount=playerCount;
        for(int i=0;i<playerCount;i++){
            playerStates.add(new ScoreHandler());
        }
    }
    public int roll(int playerIndex,int pins){
        if(playerIndex>0 && playerIndex<=playerCount){
            if(!playerStates.get(playerIndex-1).roll(pins)){
                return 0;
            }else{
                return 1;
            }
        }
        return -1;
    }
    public int getScore(int playerIndex){
        return playerStates.get(playerIndex-1).score();
    }
}
