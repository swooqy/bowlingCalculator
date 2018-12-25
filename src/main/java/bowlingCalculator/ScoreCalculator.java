package bowlingCalculator;


import java.util.ArrayList;
import java.util.stream.IntStream;

public class ScoreCalculator {
    protected static int calculateScore(ArrayList<Frame> frames){
        int cumulativeScore=0;
        for(int i=0;i<10;i++){
            cumulativeScore+=countFrame(frames,i);
        }
        return cumulativeScore;
    }
    private static int countFrame(ArrayList<Frame>frames,int index){
        Frame current=frames.get(index);
        int sum=IntStream.of(current.ballThrows).sum();
        if(sum==10){
            if(current.ballThrows[0]!=10){
                sum+=frames.get(index+1).ballThrows[0];
            }else{
                int nextThrow=frames.get(index+1).ballThrows[0];
                sum+=nextThrow;
                if(nextThrow!=10){
                    nextThrow=frames.get(index+1).ballThrows[1];
                    sum+=nextThrow;
                }else{
                    nextThrow=frames.get(index+2).ballThrows[0];
                    sum+=nextThrow;
                }
            }
        }
        return sum;
    }
}
