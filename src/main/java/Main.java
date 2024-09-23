import java.io.File;
import java.util.List;

public final class Main {

    private static int computerScore = 0;
    private static int playerScore = 0;
    private static int roundCounter = 0;
    private static int drawCounter = 0;
    private static DifficultyState difficulty;
    private static final File FILE_SCORE = new File("score.txt");

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

        if (!PlayerInput.newGame()){
            if (FILE_SCORE.exists() && !FileReadWrite.isEmpty(FILE_SCORE)){
                List<Integer> fileInput;
                fileInput = FileReadWrite.readFile(FILE_SCORE);
                roundCounter = fileInput.get(0);
                playerScore = fileInput.get(1);
                computerScore = fileInput.get(2);
                drawCounter = fileInput.get(3);
            }
        }


        PlayerInput.askForDifficulty();

        //for(int i = 0; i < 1000000; i++){
        do {
            Match match = new Match();
            match.setPlayerTurn(roundCounter % 2 == 0);

            match.play(mediumDifficultyPercentage);
            MatchStatus status = match.getStatus();


            switch (status) {
                case PLAYER_WON -> {
                    playerScore++;
                    Output.printWhoWon(match.getPlayerTurn());
                }
                case COMPUTER_WON -> {
                    computerScore++;
                    Output.printWhoWon(match.getPlayerTurn());
                }
                case DRAW ->  {
                    drawCounter++;
                    Output.printDraw();
                }
                default -> System.out.println("Invalid match status");
            }

            Output.printScore(playerScore, computerScore);
            Output.printDrawCounter(drawCounter);
            roundCounter++;
            Output.printRoundCounter(roundCounter);

            FileReadWrite.writeFile(FILE_SCORE,roundCounter, playerScore, computerScore, drawCounter);
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