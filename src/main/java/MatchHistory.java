import java.util.ArrayList;
import java.util.List;

public class MatchHistory {
    private static final int MAX_HISTORY_SIZE = 10;

    private final List<Match> matches = new ArrayList<>();

    //TODO: constructor to get the instance
    // injection, check Teams

    public static MatchHistory fromFile() {
        return FileWriteRead.getInstance().readFromHistoryFile(Main.FILE_MATCH_HISTORY);
    }

    public void printMatchHistory(MatchHistory history) {

        if (history != null) {
            int counter = 0;
            for(Match match : history.getMatches().reversed()){
                counter++;
                PrintService.getInstance().printBoardNr(counter);
                match.printBoard();
                PrintService.getInstance().printSecondsElapsed(match.getStartTime(), match.getEndTime());
                PrintService.getInstance().printDate(match.getStartTime());
            }
        }
    }

    public void addMatch(Match match) {
        matches.add(match);

        if (matches.size() > MAX_HISTORY_SIZE) {
            matches.removeFirst();
        }
    }

    public List<Match> getMatches() {
        return matches;
    }
}
