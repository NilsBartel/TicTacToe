import java.io.File;


public final class Main {

//    public static final File FILE_SCORE = new File("score.txt");
//    static String userName;
//    public static File FILE_MATCH_HISTORY = new File("match_"+userName+"history.json");


    private Main() {

    }

    public static void main(String[] args) {

        //AnalyseService.findBestWinningLine();

        StartGame startGame = new StartGame();
        startGame.start();

//        MakeComputerPlayRandom random = new MakeComputerPlayRandom();
//        random.start();
    }

}