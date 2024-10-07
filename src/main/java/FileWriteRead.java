
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


    public void writeFile(File fileName, Score score){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(fileName, score);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing file!");
        }
    }

    public Score readFile(File fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(fileName, Score.class);

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading file!");
        }
        return new Score();
    }




    public void writeToHistoryFile(File file, MatchHistory matchHistoryJson) {

        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(file, matchHistoryJson);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing file!");
        }
    }

    public MatchHistory readFromHistoryFile(File file) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(file, MatchHistory.class);

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading file!");
        }
        return new MatchHistory();
    }

}
