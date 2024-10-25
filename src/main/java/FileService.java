import java.io.File;

public final class FileService {
    private static FileService instance;
    private File fileMatchHistory;
    private final File fileUserData = new File("userData.json");

    private FileService() {
    }
    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
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
