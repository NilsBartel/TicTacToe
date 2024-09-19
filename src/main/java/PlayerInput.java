import java.util.Scanner;

public final class PlayerInput {
    private PlayerInput() {
    }

    public static Position askForMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        int input;
        do{
            System.out.println("Please pick a field (1-9)");
            input= scanner.nextInt();
        } while(!board.isValid(input));

        return new Position(input);
    }

    public static boolean askPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        String response;

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        response = scanner.nextLine();

        while(! ("y".equals(response) || "n".equals(response))) {
            System.out.println("This is not a valid input! Please try again.");
            response = scanner.nextLine();
        }

        return "y".equals(response);
    }

    public static void askForDifficulty() {
        Scanner scanner = new Scanner(System.in);
        String response;
        System.out.println("What difficulty would you like to play?   Easy, Medium, impossible?   (Type e/m/i)");
        response = scanner.nextLine();
        while(! ("e".equals(response) || "m".equals(response) || "i".equals(response))) {
            System.out.println("This is not a valid input! Please try again.");
            response = scanner.nextLine();
        }

        switch(response){
            case "e": Main.setDifficulty(DifficultyState.EASY);
            break;
            case "i": Main.setDifficulty(DifficultyState.IMPOSSIBLE);
            break;
            default: Main.setDifficulty(DifficultyState.MEDIUM);
        }
    }


}
