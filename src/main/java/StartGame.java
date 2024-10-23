public class StartGame {


    public void start() {
        PrintService printService = PrintService.getInstance();
        PlayerInput playerInput = PlayerInput.getInstance();
        FileWriteRead fileWriteRead = FileWriteRead.getInstance();


        FileService.getInstance().setFileName(playerInput.askForName());

        DifficultyState difficulty = null;
        do {

            MatchHistory matchHistory = fileWriteRead.readFromHistoryFile(FileService.getInstance().getFileMatchHistory());
            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficulty, fileWriteRead);
            difficulty = match.getDifficulty();

            match.play(matchHistory);

            Score score = StartGameUtil.updateScore(match, printService, matchHistory);

            displayScore(score);
            fileWriteRead.writeToHistoryFile(FileService.getInstance().getFileMatchHistory(), matchHistory);

            matchHistory = fileWriteRead.readFromHistoryFile(FileService.getInstance().getFileMatchHistory());
            printService.printAnalysedWinPositions(AnalyseService.getInstance().findBestWinningLine(matchHistory));
        } while (playerInput.askPlayAgainWithHistory());

        printService.printGameEndMessage();
    }




    private void displayScore(Score score) {
        PrintService.getInstance().printScore(score);
        PrintService.getInstance().printDrawCounter(score);
        PrintService.getInstance().printRoundCounter(score);
    }

}
