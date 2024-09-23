import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileReadWrite {
    private FileReadWrite() {
    }


    public static boolean isEmpty(File file) {
        return file.exists() && file.length() == 0;
    }

    public static List<Integer> readFile(File fileName) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();
            for(int i = 0; i < 4; i++){
                list.add(Integer.parseInt(line.split("\t")[i]));
            }
        } catch (IOException e) {
            System.out.println("error while reading file");
        }

        return list;
    }

    public static void writeFile(File fileName, int rounds, int playerScore, int computerScore, int draw){
        String string = rounds + "\t" + playerScore + "\t" + computerScore + "\t" + draw;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(string);
        } catch (IOException e) {
            System.out.println("error while writing file");
        }
    }

}
