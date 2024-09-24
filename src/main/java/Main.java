import java.io.File;
import java.util.List;

public final class Main {

//    private static int computerScore = 0;
//    private static int playerScore = 0;
//    private static int roundCounter = 0;
//    private static int drawCounter = 0;



    private static DifficultyState difficulty;
    private static final File FILE_SCORE = new File("score.txt");
    private static final File FILE_SCORE2 = new File("score2.txt");
    private static final File FILE_SCORE3 = new File("score3.txt");

    private Main() {
    }



    @SuppressWarnings("PMD.LawOfDemeter")
    public static void main(String[] args) {



//        int input = 0;
//        if (args.length > 0) {
//            try {
//                input = Integer.parseInt(args[0]);
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number between 1 and 100");
//                System.exit(1);
//            }
//            if (input < 1 || input > 100) {
//                System.out.println("Please enter a number between 1 and 100");
//                System.exit(1);
//            }
//        } else {
//            System.out.println("Please enter a number between 1 and 100");
//            System.exit(1);
//        }
//        int mediumDifficultyPercentage = input;


        int mediumDifficultyPercentage = 40;

        System.out.println("Welcome to TicTacToe!");
        System.out.println();

        PlayerInput.askForDifficulty();

        //for(int i = 0; i < 1000000; i++){
        do {

            Match match = new Match();


            //TODO: work on who starts (player and computer alternating)
//            if (FILE_SCORE.exists() && !FileReadWrite.isEmpty(FILE_SCORE)) {
//                match.setPlayerTurn((FileReadWrite.readFile(FILE_SCORE).get(0) + FileReadWrite.readFile(FILE_SCORE).get(1) + FileReadWrite.readFile(FILE_SCORE).get(2)) % 2 == 0);
//            } else match.setPlayerTurn(true);
            match.setPlayerTurn(true);


            match.play(mediumDifficultyPercentage);
            MatchStatus status = match.getStatus();

            Score score;
            if (FILE_SCORE.exists() && FILE_SCORE.length() != 0) {
                //FileReadWrite.readFile(FILE_SCORE, score);
                score = JsonFileWriteRead.readFile(FILE_SCORE);
            } else score = new Score();


            switch (status) {
                case PLAYER_WON -> {
                    score.setPlayerScorePlusOne();
                    Output.printWhoWon(match.isPlayerTurn());
                }
                case COMPUTER_WON -> {
                    score.setComputerScorePlusOne();
                    Output.printWhoWon(match.isPlayerTurn());
                }
                case DRAW ->  {
                    score.setDrawCountPlusOne();
                    Output.printDraw();
                }
                default -> System.out.println("Invalid match status");
            }



            Output.printScore(score);
            Output.printDrawCounter(score);
            Output.printRoundCounter(score);

            //FileReadWrite.writeFile(FILE_SCORE, score);
            JsonFileWriteRead.writeFile(FILE_SCORE, score);
        } while (PlayerInput.askPlayAgain());






        //}
        //Output.printScore(playerScore, computerScore);
        //Output.printRoundCounter(roundCounter);
        //Output.printDrawCounter(drawCounter);


        Output.printGameEndMessage();
    }

    public static DifficultyState getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(DifficultyState difficulty) {
        Main.difficulty = difficulty;
    }

}