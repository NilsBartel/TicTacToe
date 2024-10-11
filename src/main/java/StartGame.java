public class StartGame {

    private PrintService printService;
    private PlayerInput playerInput;
    private FileWriteRead fileWriteRead;
    private Match match;
    //TODO: do i need all these?


    //@SuppressWarnings("PMD.LawOfDemeter")
    public void start() {

        MatchHistory matchHistory = fileWriteRead.readFromHistoryFile(Main.FILE_MATCH_HISTORY);
        DifficultyState difficulty = null;

        if (!StartGameUtil.loadAbandonedGame(matchHistory)) {
            difficulty = playerInput.askForDifficulty();
        }


        do {

            matchHistory = fileWriteRead.readFromHistoryFile(Main.FILE_MATCH_HISTORY);
            match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficulty); //TODO: test this StartGameUtil

            match.play(matchHistory);

            Score score = updateScore(match);
            displayScore(score);

        } while (playerInput.askPlayAgainWithHistory());

        printService.printGameEndMessage();
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
            case NOT_STARTED, RUNNING -> {}
            default -> System.out.println("Invalid match status (in StartGame)");
        }
        FileWriteRead.getInstance().writeFile(Main.FILE_SCORE, score);

        return score;
    }

    private void displayScore(Score score) {
        PrintService.getInstance().printScore(score);
        PrintService.getInstance().printDrawCounter(score);
        PrintService.getInstance().printRoundCounter(score);
    }

    public PrintService getPrintService() {
        return printService;
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }

    public PlayerInput getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(PlayerInput playerInput) {
        this.playerInput = playerInput;
    }

    public FileWriteRead getFileWriteRead() {
        return fileWriteRead;
    }

    public void setFileWriteRead(FileWriteRead fileWriteRead) {
        this.fileWriteRead = fileWriteRead;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
