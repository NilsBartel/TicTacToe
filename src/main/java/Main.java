import java.io.File;


public final class Main {

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


        Match match;

        MatchHistory matchHistory = FileWriteRead.getInstance().readFromHistoryFile(FILE_MATCH_HISTORY);
        DifficultyState difficulty;

        if (!matchHistory.getMatches().isEmpty() && matchHistory.getMatches().getLast().getStatus() == MatchStatus.RUNNING) { //TODO: LawOfDemeter ???
            System.out.println("Welcome back, your last game has been restored.");
            System.out.println();
            match = matchHistory.getMatches().getLast();
            difficulty = match.getDifficulty();

        } else {
            System.out.println("Welcome to TicTacToe!");
            System.out.println();
            match = new Match();
            difficulty = PlayerInput.getInstance().askForDifficulty();
            match.setDifficulty(difficulty);
            //match.setDifficulty(PlayerInput.getInstance().askForDifficulty());
        }





        //int mediumDifficultyPercentage = 40; //TODO: save in file???
        //TODO: play again is wrong
        //TODO: is the player turn correct??? (after reload)



        do {

            if(match.getStatus() != MatchStatus.RUNNING && match.getStatus() != MatchStatus.NOT_STARTED) {
                match = new Match();
                match.setDifficulty(difficulty);
            }

            match.play();
            MatchStatus status = match.getStatus();

            Score score = FileWriteRead.getInstance().readFile(FILE_SCORE);
            
            switch (status) {
                case PLAYER_WON -> {
                    score.setPlayerScorePlusOne();
                    PrintService.getInstance().printWhoWon(match.isIsPlayerTurn());
                }
                case COMPUTER_WON -> {
                    score.setComputerScorePlusOne();
                    PrintService.getInstance().printWhoWon(match.isIsPlayerTurn());
                }
                case DRAW -> {
                    score.setDrawCountPlusOne();
                    PrintService.getInstance().printDraw();
                }
                default -> System.out.println("Invalid match status (in Main)");
            }


            PrintService.getInstance().printScore(score);
            PrintService.getInstance().printDrawCounter(score);
            PrintService.getInstance().printRoundCounter(score);

            FileWriteRead.getInstance().writeFile(FILE_SCORE, score);
        } while (PlayerInput.getInstance().askPlayAgainWithHistory());

        PrintService.getInstance().printGameEndMessage();
    }

}