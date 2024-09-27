
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public final class JsonFileWriteRead {
    private JsonFileWriteRead() {
    }

    //TODO: just return null instead of throwing RuntimeException.

    public static void writeFile(File fileName, Score score){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(fileName, score);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing file!");
        }
    }

    public static Score readFile(File fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(fileName, Score.class);

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading file!");
        }
        return null;
    }


    public static void writeHistoryFile(File fileName, MatchHistory matchHistory){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(fileName, matchHistory);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing file!");
        }
    }

    public static MatchHistory readHistoryFile(File fileName){
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(fileName, MatchHistory.class);

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading file!");
        }
        return null;
    }


}
