
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public final class JsonFileWriteRead {
    private JsonFileWriteRead() {
    }



    public static void writeFile(File fileName, Score score){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(fileName, score);
        } catch (IOException e) {
            System.out.println("error while writing file");
            throw new RuntimeException(e);
        }
    }


    public static Score readFile(File fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(fileName, Score.class);

        } catch (IOException e) {
            System.out.println("error while reading file");
            throw new RuntimeException(e);
        }
    }


    public static MatchHistory readHistoryFile(File fileName){
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(fileName, MatchHistory.class);

        } catch (IOException e) {
            System.out.println("error while reading file");
            throw new RuntimeException(e);
        }
    }

    public static void writeHistoryFile(File fileName, MatchHistory matchHistory){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(fileName, matchHistory);
        } catch (IOException e) {
            System.out.println("error while writing file");
            throw new RuntimeException(e);
        }
    }









}
