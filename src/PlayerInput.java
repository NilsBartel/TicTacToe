import java.util.Scanner;

public class PlayerInput {





    public static Position askForMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        int n;
        do{
            System.out.println("Please pick a field (1-9)");
            n= scanner.nextInt();
        } while(!board.isValid(n));

        return new Position(n);
    }

    public static boolean askPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        String response;

        /*System.out.println();
        System.out.println("The score is:");
        System.out.println("Player Score: " + playerScore + "      " + "Computer Score: " + computerScore);*/

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        response = scanner.nextLine();

        while(! (response.equals("y") || response.equals("n"))){
            System.out.println("This is not a valid input! Please try again.");
            response = scanner.nextLine();
        }

        return response.equals("y");
    }


}
