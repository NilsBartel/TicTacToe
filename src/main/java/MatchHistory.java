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
            counter++;
            printService.printBoardNr(counter);
            printService.printBoard(match);
            printService.printSecondsElapsed(match.getStartTime(), match.getEndTime());
            printService.printDate(match.getStartTime());
        }
    }


    // 10
    // if > 10 -> remove last and add
    //  running: 11
    //  running: 11
    // end
    //


    public void addRunningMatch(Match match) {
        this.matches.add(match);
    }

    public void addFinishedMatch(Match match) {
        this.matches.add(match);

        if (matches.size() > MAX_HISTORY_SIZE) {

            matches.removeFirst();
        }
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
