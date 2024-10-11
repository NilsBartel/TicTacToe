import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class MatchHistory {
    public static final int MAX_HISTORY_SIZE = 10;

    private final List<Match> matches = new ArrayList<>();

    @JsonIgnore
    private PrintService printService;


    public static MatchHistory fromFile() {
        PrintService printService = PrintService.getInstance();

        MatchHistory history = FileWriteRead.getInstance().readFromHistoryFile(Main.FILE_MATCH_HISTORY);
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

    private MatchStatus getLastStatus() {
        return this.matches.getLast().getStatus();
    }

    public boolean compareLastStatus(MatchStatus status) {
        return getLastStatus() == status;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public PrintService getPrintService() {
        return printService;
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }


}
