import java.util.Scanner;

public class PlayerMoveService {





    public static int askForMove(){
        System.out.println("Please pick a field (1-9)");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
