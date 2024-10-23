import java.io.File;

public final class FileService {
    private static FileService instance;
    private FileService() {
    }
    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    private final File FILE_SCORE = new File("score.txt");
    private File FILE_MATCH_HISTORY;


    public void setFileName(String fileName) {
        FILE_MATCH_HISTORY = new File("matchHistory"+fileName+".json");
    }

    public File getFILE_MATCH_HISTORY() {
        return FILE_MATCH_HISTORY;
    }

    public File getFILE_SCORE() {
        return FILE_SCORE;
    }
}
