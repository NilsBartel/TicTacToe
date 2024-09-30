
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public final class JsonFileWriteRead {
    private JsonFileWriteRead() {
    }


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

    public static void writeHistoryFileMatch(Match match){
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("testFile.json");
        try{
            mapper.writeValue(file, match);
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











    public static void writeToHistoryFile(File file, MatchHistoryJson matchHistoryJson) {

        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(file, matchHistoryJson);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while writing file!");
        }
    }

    public static MatchHistoryJson readToHistoryFile(File file) {
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(file, MatchHistoryJson.class);

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error while reading file!");
        }
        return null;
    }

}
