import java.io.File;


public final class Main {

    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");


    private Main() {
    }
        //TODO: Tests (for after restart)


    @SuppressWarnings("PMD.LawOfDemeter")
    public static void main(String[] args) {

        Match match;

        MatchHistory matchHistory = FileWriteRead.getInstance().readFromHistoryFile(FILE_MATCH_HISTORY);
        DifficultyState difficulty = null;

        if (!matchHistory.getMatches().isEmpty() && (matchHistory.getMatches().getLast().getStatus() == MatchStatus.RUNNING || matchHistory.getMatches().getLast().getStatus() == MatchStatus.NOT_STARTED)) {
            System.out.println("Welcome back, your last game has been restored.");
            System.out.println();

        } else {
            System.out.println("Welcome to TicTacToe!");
            System.out.println();
            difficulty = PlayerInput.getInstance().askForDifficulty();
        }


        do {

            if (!matchHistory.getMatches().isEmpty() && (matchHistory.getMatches().getLast().getStatus() == MatchStatus.RUNNING || matchHistory.getMatches().getLast().getStatus() == MatchStatus.NOT_STARTED)) {
                match = matchHistory.getMatches().getLast();
                difficulty = match.getDifficulty();
            } else {
                match = new Match();
                match.setDifficulty(difficulty);
                matchHistory.addMatch(match);
                FileWriteRead.getInstance().writeToHistoryFile(FILE_MATCH_HISTORY, matchHistory);
            }



            match.play(matchHistory);
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
                case MATCH_ALREADY_FINISHED -> {
                    //TODO: do something (not print out or the score stuff)?
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