import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileReadWrite {
    private FileReadWrite() {
    }



    public static void readFile(File fileName, Score score) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();
            for(int i = 0; i < 3; i++){
                list.add(Integer.parseInt(line.split("\t")[i]));
            }
            score.setPlayerScore(list.get(0));
            score.setComputerScore(list.get(1));
            score.setDrawCount(list.get(2));

        } catch (IOException e) {
            System.out.println("error while reading file");
        }

    }

    public static void writeFile(File fileName, Score score){
        String string = score.getPlayerScore() + "\t" + score.getComputerScore() + "\t" + score.getDrawCount();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(string);
        } catch (IOException e) {
            System.out.println("error while writing file");
        }
    }

}
