import java.io.File;


public final class Main {

    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");


    private Main() {
    }
        //TODO: Tests (for after restart)
        // have the tempHistory in its own class?

    @SuppressWarnings("PMD.LawOfDemeter")
    public static void main(String[] args) {

//        Match match1 = new Match();
//        Match match2 = new Match();
//        System.out.println(match1.equals(match2));

//        Board board1 = new Board();
//        Board board2 = new Board();
//        System.out.println(board1.equals(board2));

//        Field field1 = new Field();
//        Field field2 = new Field();
//        System.out.println(field1.equals(field2));

        Match match;

        MatchHistory matchHistory = FileWriteRead.getInstance().readFromHistoryFile(FILE_MATCH_HISTORY);
        DifficultyState difficulty;

        if (!matchHistory.getMatches().isEmpty() && matchHistory.getMatches().getLast().getStatus() == MatchStatus.RUNNING) {
            System.out.println("Welcome back, your last game has been restored.");
            System.out.println();
            match = matchHistory.getMatches().getLast();
            difficulty = match.getDifficulty();

        } else {
            System.out.println("Welcome to TicTacToe!");
            System.out.println();
            match = new Match();
            matchHistory.addMatch(match);
            difficulty = PlayerInput.getInstance().askForDifficulty();
            match.setDifficulty(difficulty);
        }


        //TODO: when both games have finished and they both start their own game (one after another), second one also starts its own game even tho the first one already has one...

        do {
            if(match.isStatusEqual(MatchStatus.DRAW) || match.isStatusEqual(MatchStatus.PLAYER_WON)|| match.isStatusEqual(MatchStatus.COMPUTER_WON) || match.isStatusEqual(MatchStatus.MATCH_ALREADY_FINISHED) ) {
                //TODO: can i add all of those together ^^^^   (like one state that still keeps all these states)
                match = new Match();
                matchHistory.addMatch(match);
                match.setDifficulty(difficulty);
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