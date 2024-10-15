public class StartGame {


    public void start() {
        PrintService printService = PrintService.getInstance();
        PlayerInput playerInput = PlayerInput.getInstance();
        FileWriteRead fileWriteRead = FileWriteRead.getInstance();


        DifficultyState difficulty = null;
        do {

            MatchHistory matchHistory = fileWriteRead.readFromHistoryFile(Main.FILE_MATCH_HISTORY);
            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficulty, fileWriteRead);
            difficulty = match.getDifficulty();


            match.play(matchHistory);

            Score score = StartGameUtil.updateScore(match, fileWriteRead, printService);
            displayScore(score);

            AnalyseService.findBestWinningLine();
        } while (playerInput.askPlayAgainWithHistory());

        printService.printGameEndMessage();
    }




    private void displayScore(Score score) {
        PrintService.getInstance().printScore(score);
        PrintService.getInstance().printDrawCounter(score);
        PrintService.getInstance().printRoundCounter(score);
    }

}
