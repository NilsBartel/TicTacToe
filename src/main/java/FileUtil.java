import java.io.File;

public final class FileUtil {
    private static FileUtil instance;
    private File fileMatchHistory;
    private final File fileUserData = new File("userData.json");

    private FileUtil() {
    }
    public static FileUtil getInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
        return instance;
    }
























    public void setFileName(String fileName) {
        fileMatchHistory = new File("matchHistory"+fileName+".json");
    }

    public File getFileMatchHistory() {
        return fileMatchHistory;
    }

    public File getFileUserData() {
        return fileUserData;
    }
}
