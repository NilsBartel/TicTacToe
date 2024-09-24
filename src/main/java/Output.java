
public final class Output {
    private Output() {
    }

    public static void printWhoWon(boolean isPlayerTurn) {
        System.out.println();
        if (isPlayerTurn) {
            System.out.println("player wins!");
        } else {
            System.out.println("computer wins");
        }
    }

    public static void printDraw() {
        System.out.println();
        System.out.println("Draw");
    }

    public static void printGameEndMessage() {
        System.out.println();
        System.out.println("Game end");
    }

    public static void printScore(Score score) {
        System.out.println();
        System.out.println("Score:");
        System.out.println("player: "+ score.getPlayerScore() + "\t" + "computer: " + score.getComputerScore());
    }

    public static void printRoundCounter(Score score) {
        System.out.println();
        System.out.println("Round: " + score.getRoundCounter());
    }

    public static void printDrawCounter(Score score) {
        System.out.println("Draw counter: " + score.getDrawCount());
    }


}
