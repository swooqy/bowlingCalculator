package bowlingCalculator;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingHandlerController {

    private BowlingHandler bowlingHandler;
    private int playerCount;


    @RequestMapping("/init")
    public String initialize(@RequestParam(value="playerCount") int playerCount){
        if(playerCount>=1 && playerCount<=3){
            this.playerCount=playerCount;
            startSequence();
            return "Initiated Succesfully";
        }
        return "This ammount of players is not supported";
    }
    @RequestMapping("/reset")
    public String reset(){
        return initialize(playerCount);
    }

    private void startSequence(){
        bowlingHandler=new BowlingHandler(playerCount);
    }

    @RequestMapping("/roll")
    public String roll(@RequestParam(value="player") int player,
                           @RequestParam(value="pins") int pins) {
        if(!checkInit()){
            return "Game is not initialized";
        }
        int rolled=bowlingHandler.roll(player,pins);
        switch(rolled){
            case 1: {
                return "Rolled Succesfully";
            }
            case 0:{
                return score(player);
            }
            default:
                return "Wrong Player Number";
        }
    }
    @RequestMapping("/score")
    public String score(@RequestParam(value="player") int player){
        if(!checkInit()){
            return "Game is not initialized";
        }
        if(player>playerCount || player<=0){
            return "Wrong Player Number";
        }
        int curScore=bowlingHandler.getScore(player);
        if(curScore==-1){
            return "Game is not yet finished";
        }else{
            return Integer.toString(curScore);
        }
    }
    private boolean checkInit(){
        if(bowlingHandler==null){
            return false;
        }else{
            return true;
        }
    }
}
