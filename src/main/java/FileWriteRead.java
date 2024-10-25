
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public final class FileWriteRead {

    private static FileWriteRead instance;
    private FileWriteRead() {
    }

    public static FileWriteRead getInstance() {
        if (instance == null) {
            instance = new FileWriteRead();
        }
        return instance;
    }


    public void writeToHistoryFile(File file, MatchHistory matchHistoryJson) {

        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(file, matchHistoryJson);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing to MatchHistory file!");
        }
    }

    public MatchHistory readFromHistoryFile(File file) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(file, MatchHistory.class);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading from MatchHistory file!");
        }
        return new MatchHistory();
    }




    public void writeToUserFile(File file, User user) {

        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(file, user);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing  to User file!");
        }
    }
    public User readFromUserFile(File file) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(file, User.class);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading from User file!");
        }
        return new User();
    }






    public boolean compareToLastMatchState(MatchStatus matchStatus) {
        return readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast().isStatusEqual(matchStatus);
    }

    public boolean compareToLastMatchStartTime(Long startTime) {
        return readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast().getStartTime() == startTime;
    }

    public Match getLastMatch() {
        return readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast();
    }

    public Board getLastBoard() {
        return readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast().getBoard();
    }




}
