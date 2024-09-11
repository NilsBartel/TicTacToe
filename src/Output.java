public class Output {


    public static void printWhoWon(boolean isPlayerTurn) {
        System.out.println();
        if (isPlayerTurn) {
            System.out.println("player wins!");
        } else System.out.println("computer wins");
    }

    public static void printDraw() {
        System.out.println();
        System.out.println("Draw");
    }

    public static void printGameEndMessage() {
        System.out.println();
        System.out.println("Game end");
    }

    public static void printScore(int playerScore, int computerScore) {
        System.out.println();
        System.out.println("Score:");
        System.out.println("player: "+ playerScore + "\t" + "computer: " + computerScore);
    }


}
