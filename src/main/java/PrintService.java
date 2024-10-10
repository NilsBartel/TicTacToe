
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




    public void printWhoWon(boolean isPlayerTurn) {
        System.out.println();
        if (isPlayerTurn) {
            System.out.println("player wins!");
        } else {
            System.out.println("computer wins");
        }
    }

    public void printDraw() {
        System.out.println();
        System.out.println("Draw");
    }

    public void printGameEndMessage() {
        System.out.println();
        System.out.println("Game end");
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

}
