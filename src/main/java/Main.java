import java.io.File;


public final class Main {

    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");


    private Main() {
    }

    //TODO: make it run a certain amount of times again against random

    public static void main(String[] args) {

        //AnalyseService.findBestWinningLine();

        StartGame startGame = new StartGame();
        startGame.start();
    }

}