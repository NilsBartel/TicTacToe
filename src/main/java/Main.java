import java.io.File;


public final class Main {


    private static DifficultyState difficulty;
    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");



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

        do {

            Match match = new Match();

            match.play(mediumDifficultyPercentage);
            MatchStatus status = match.getStatus();


            Score score = null;
            if (FILE_SCORE.exists() && FILE_SCORE.length() != 0 && FileWriteRead.readFile(FILE_SCORE) != null) {
                score = FileWriteRead.readFile(FILE_SCORE);
            }
            if (score == null) {
                score = new Score();
            }
            
            switch (status) {
                case PLAYER_WON -> {
                    score.setPlayerScorePlusOne();
                    Output.printWhoWon(match.isIsPlayerTurn());
                }
                case COMPUTER_WON -> {
                    score.setComputerScorePlusOne();
                    Output.printWhoWon(match.isIsPlayerTurn());
                }
                case DRAW -> {
                    score.setDrawCountPlusOne();
                    Output.printDraw();
                }
                default -> System.out.println("Invalid match status");
            }


            Output.printScore(score);
            Output.printDrawCounter(score);
            Output.printRoundCounter(score);

            FileWriteRead.writeFile(FILE_SCORE, score);
        } while (PlayerInput.askPlayAgainWithHistory());

        Output.printGameEndMessage();
    }

    public static DifficultyState getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(DifficultyState difficulty) {
        Main.difficulty = difficulty;
    }
}