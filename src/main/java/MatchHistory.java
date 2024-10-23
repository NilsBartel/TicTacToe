import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MatchHistory {
    public static final int MAX_HISTORY_SIZE = 10;

    private final List<Match> matches = new ArrayList<>();
    private Score score;
    //private List<Score> scores = new ArrayList<>();

    @JsonIgnore
    private PrintService printService;


    public static MatchHistory fromFile(File file) {
        PrintService printService = PrintService.getInstance();

        MatchHistory history = FileWriteRead.getInstance().readFromHistoryFile(file);
        history.setPrintService(printService);
        return history;
    }

    public void printMatchHistory() {

        int counter = 0;
        for(Match match : this.getMatches().reversed()){

            if(counter < MAX_HISTORY_SIZE && (match.isStatusEqual(MatchStatus.DRAW) || match.isStatusEqual(MatchStatus.PLAYER_WON)|| match.isStatusEqual(MatchStatus.COMPUTER_WON))) {  //match.getStatus() == MatchStatus.DRAW
                counter++;
                printService.printBoardNr(counter);
                printService.printBoard(match);
                printService.printSecondsElapsed(match.getStartTime(), match.getEndTime());
                printService.printDate(match.getStartTime());
            }
        }
    }



    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public void updateScore(Score score) {
        this.score = score;
    }

    private MatchStatus getLastStatus() {
        return this.matches.getLast().getStatus();
    }

    public boolean compareLastStatus(MatchStatus status) {
        return getLastStatus() == status;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Score getScore() {
        if(score == null) {
            score = new Score();
        }
        return score;
    }

    public void setScores(Score score) {
        this.score = score;
    }

    public PrintService getPrintService() {
        return printService;
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }


}
