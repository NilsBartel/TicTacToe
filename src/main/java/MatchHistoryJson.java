import java.util.ArrayList;
import java.util.List;

public class MatchHistoryJson {
    private static final int MAX_HISTORY_SIZE = 10;

    private final List<Match> matches = new ArrayList<>();


    public void printMatchHistory(){
        if (JsonFileWriteRead.readToHistoryFile(Main.FILE_MATCH_HISTORY_NEW) != null) {
            MatchHistoryJson history = JsonFileWriteRead.readToHistoryFile(Main.FILE_MATCH_HISTORY_NEW);

            int counter = 0;
            for(Match match : history.getMatches().reversed()){
                counter++;
                System.out.println();
                System.out.println("Board: " + counter);
                match.getBoard().print();   //TODO: Law Of Demeter
                System.out.println(TimeUtility.convertToSeconds(List.of(match.getStartTime(), match.getEndTime())) + " seconds");   //TODO: change from list to individual arguments
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
