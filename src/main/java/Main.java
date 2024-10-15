import java.io.File;


public final class Main {

    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");


    private Main() {
    }



    public static void main(String[] args) {

        AnalyseService.findBestWinningLine();

//        StartGame startGame = new StartGame();
//        startGame.start();
    }

}