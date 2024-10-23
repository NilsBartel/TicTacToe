import java.io.File;

public class MakeComputerPlayRandom {

    public static final File SCORE_FILE_TEST = new File("scoresTest.txt");
    public static final File HISTORY_FILE_TEST = new File("historyTest.json");

    public void start() {
        PrintService printService = PrintService.getInstance();
        FileWriteRead fileWriteRead = FileWriteRead.getInstance();

        //TODO: just save the history
        Score score = null;
        DifficultyState difficulty = DifficultyState.IMPOSSIBLE;

        for (int i = 0; i < 7500; i++) {
            System.out.println(i);
            MatchHistory matchHistory = FileWriteRead.getInstance().readFromHistoryFile(HISTORY_FILE_TEST);

            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficulty, fileWriteRead);

            match.play(matchHistory);

            score = StartGameUtil.updateScore(match, printService, matchHistory);
        }


        printService.printScore(score);
        printService.printDrawCounter(score);
        printService.printRoundCounter(score);
        MatchHistory matchhistory = MatchHistory.fromFile(HISTORY_FILE_TEST);
        PrintService.getInstance().printAnalysedWinPositions(AnalyseService.getInstance().findBestWinningLine(matchhistory));

        printService.printGameEndMessage();

        HISTORY_FILE_TEST.delete();
        SCORE_FILE_TEST.delete();
    }




}



