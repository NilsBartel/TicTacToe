import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("PMD.TooManyMethods")
public final class PrintService {
    private static PrintService instance;

    private PrintService() {
    }
    public static PrintService getInstance() {
        if (instance == null) {
            instance = new PrintService();
        }
        return instance;
    }

    public void printSecondsElapsed(Long startTime, Long endTime) {
        System.out.println(TimeUtility.convertToSeconds(startTime, endTime) + " seconds");
    }

    public void printDate(Long milliseconds) {
        TimeUtility.printDate(milliseconds);
    }

    public void printBoardNr(int counter) {
        System.out.println();
        System.out.println("Board: " + counter);
    }

    public void printBoard(Match match) {
        match.printBoard();
    }



    public void printRow(String row) {
        System.out.println(row);
    }

    public void printAnalysedWinPositions(Map<List<Position>, Integer> map) {

        int size = map.size();
        System.out.println();
        for (int i = 0; i < size; i++) {
            List<Position> winPositions = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
            int count =  Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
            map.remove(winPositions);

            for (Position position : winPositions) {
                System.out.print(position.getIndex() + " ");
            }
            System.out.println("- Happened "+count+" times.");
        }
    }


    public void printComputerWon(){
        System.out.println();
        System.out.println("computer wins!");
    }

    public void printPlayerWon() {
        System.out.println();
        System.out.println("player wins!");
    }

    public void printDraw() {
        System.out.println();
        System.out.println("Draw!");
    }

    public void printGameEndMessage() {
        System.out.println();
        System.out.println("Game end!");
    }

    public void printScore(Score score) {
        System.out.println();
        System.out.println("Score:");
        System.out.println("player: "+ score.getPlayerScore() + "\t" + "computer: " + score.getComputerScore());
    }

    public void printRoundCounter(Score score) {
        System.out.println();
        System.out.println("Round: " + score.getRoundCounter());
    }

    public void printDrawCounter(Score score) {
        System.out.println("Draw counter: " + score.getDrawCount());
    }

    public void printInvalidStatus() {
        System.out.println("Invalid match status!");
    }

}
