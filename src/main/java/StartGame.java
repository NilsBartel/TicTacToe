public class StartGame {


    //@SuppressWarnings("PMD.LawOfDemeter")
    public void start() {
        Match match;

        MatchHistory matchHistory = FileWriteRead.getInstance().readFromHistoryFile(Main.FILE_MATCH_HISTORY);
        DifficultyState difficulty = null;

        if (!matchHistory.getMatches().isEmpty() && (matchHistory.compareLastStatus(MatchStatus.RUNNING) || matchHistory.compareLastStatus(MatchStatus.NOT_STARTED))) {
            System.out.println("Welcome back, your last game has been restored.");
            System.out.println();

        } else {
            System.out.println("Welcome to TicTacToe!");
            System.out.println();
            difficulty = PlayerInput.getInstance().askForDifficulty();
        }


        do {
            matchHistory = FileWriteRead.getInstance().readFromHistoryFile(Main.FILE_MATCH_HISTORY);
            if (!matchHistory.getMatches().isEmpty() && (matchHistory.compareLastStatus(MatchStatus.RUNNING) || matchHistory.compareLastStatus(MatchStatus.NOT_STARTED) || matchHistory.compareLastStatus(MatchStatus.MATCH_ALREADY_FINISHED))) {

                match = matchHistory.getMatches().getLast();
                difficulty = match.getDifficulty();
                System.out.println("loaded match");
            } else {
                match = new Match();
                match.setDifficulty(difficulty);
                matchHistory.addMatch(match);
                FileWriteRead.getInstance().writeToHistoryFile(Main.FILE_MATCH_HISTORY, matchHistory);
                System.out.println("new match");
            }



            match.play(matchHistory);

            Score score = updateScore(match);
            PrintService.getInstance().printScore(score);
            PrintService.getInstance().printDrawCounter(score);
            PrintService.getInstance().printRoundCounter(score);


        } while (PlayerInput.getInstance().askPlayAgainWithHistory());

        PrintService.getInstance().printGameEndMessage();
    }

    private Score updateScore(Match match) {
        MatchStatus status = match.getStatus();
        Score score = FileWriteRead.getInstance().readFile(Main.FILE_SCORE);

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
        FileWriteRead.getInstance().writeFile(Main.FILE_SCORE, score);

        return score;
    }


}
