import java.util.ArrayList;
import java.util.List;

public class MatchHistory {
    private static final int MAX_HISTORY_SIZE = 10;

    private final List<Match> matches = new ArrayList<>();


    public void printMatchHistory(){
        if (FileWriteRead.readFromHistoryFile(Main.FILE_MATCH_HISTORY) != null) {
            MatchHistory history = FileWriteRead.readFromHistoryFile(Main.FILE_MATCH_HISTORY);

            int counter = 0;
            for(Match match : history.getMatches().reversed()){
                counter++;
                System.out.println();
                System.out.println("Board: " + counter);
                match.printBoard();
                System.out.println(TimeUtility.convertToSeconds(match.getStartTime(), match.getEndTime()) + " seconds");
                TimeUtility.printDate(match.getStartTime());
            }

        } else {
            System.out.println("No history found");
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
