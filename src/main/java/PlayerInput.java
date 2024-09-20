import java.util.Scanner;

public final class PlayerInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    private PlayerInput() {
    }

    public static Position askForMove(Board board) {
        int input;
        do{
            System.out.println("Please pick a field (1-9)");
            input= SCANNER.nextInt();
        } while(!board.isValid(input));

        return new Position(input);
    }

    public static boolean askPlayAgain() {
        String response;

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        response = SCANNER.nextLine();

        while(! ("y".equals(response) || "n".equals(response))) {
            System.out.println("This is not a valid input! Please try again.");
            response = SCANNER.nextLine();
        }

        return "y".equals(response);
    }

    public static void askForDifficulty() {
        String response;
        System.out.println("What difficulty would you like to play?   Easy, Medium, impossible?   (Type e/m/i)");
        response = SCANNER.nextLine();

        while(! ("e".equals(response) || "m".equals(response) || "i".equals(response))) {
            System.out.println("This is not a valid input! Please try again.");
            response = SCANNER.nextLine();
        }

        switch(response){
            case "e": Main.setDifficulty(DifficultyState.EASY);
            break;
            case "m": Main.setDifficulty(DifficultyState.MEDIUM);
            break;
            case "i": Main.setDifficulty(DifficultyState.IMPOSSIBLE);
            break;
            default: {throw new IllegalStateException("Unexpected value: " + response);}
        }
    }


}
