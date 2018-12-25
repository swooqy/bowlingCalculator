package bowlingCalculator;


import java.util.ArrayList;
import java.util.stream.*;

public class ScoreHandler {
    private ArrayList<Frame> frames=new ArrayList<Frame>();
    private int finalScore;
    private boolean scoreCounted;
    private Frame currentFrame;
    private int currentFrameIndex;
    private int currentThrow;
    private boolean gameFinished;
    private int fillBallsLeft;

    public ScoreHandler(){
        currentFrame=new Frame();
        currentFrameIndex=0;
        currentThrow=0;
        gameFinished=false;
        fillBallsLeft=0;
        scoreCounted=false;
    }

    public boolean roll(int pins){
        if(gameFinished){return false;}
        if(currentFrameIndex>9){
            rollFill(pins);
            return true;
        }
        currentFrame.ballThrows[currentThrow]=pins;
        if(currentThrow==1 || pins==10){
            currentThrow=0;
            currentFrameIndex++;
            frames.add(currentFrame);
            if(currentFrameIndex>9){
                startEndgame();
            }
            currentFrame=new Frame();
        }else{
            currentThrow++;
        }
        return true;
    }
    private void startEndgame(){
        if(currentFrame.ballThrows[0]==10){
            fillBallsLeft=2;
        }else if(IntStream.of(currentFrame.ballThrows).sum()==10){
            fillBallsLeft=1;
        }else{
            gameFinished=true;
        }
    }
    private void rollFill(int pins){
        currentFrame.ballThrows[currentThrow]=pins;
        fillBallsLeft--;
        if(fillBallsLeft==0){
            frames.add(currentFrame);
            gameFinished=true;
        }else{
            if(pins==10){
                frames.add(currentFrame);
                currentThrow=0;
                currentFrame=new Frame();
            }else{
                currentThrow++;
            }
        }
    }
    public int score(){
        if(!gameFinished){
            return -1;
        }else{
            if(scoreCounted){
                return finalScore;
            }else{
                finalScore=ScoreCalculator.calculateScore(frames);
                return finalScore;
            }
        }
    }

}
