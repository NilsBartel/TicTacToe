import java.io.File;


public final class Main {

    public static final File FILE_SCORE = new File("score.txt");
    public static final File FILE_MATCH_HISTORY = new File("match_history.json");


    private Main() {
    }
        //TODO: Tests (for after restart)
        //TODO: when second game starts at the right time (when no match is started) you can change the difficulty


    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        startGame.start();
    }

}