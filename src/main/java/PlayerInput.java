import java.util.Scanner;

public final class PlayerInput {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INVALID_INPUT = "This is not a valid input! Please try again.";

    private PlayerInput() {
    }

    public static Position askForMove(Board board) {
        String move;
        int number;
        while(true){
            System.out.println("Please pick a field (1-9)");
            move = SCANNER.nextLine();

            if(isInteger(move) && board.isValid(Integer.parseInt(move))){
                number = Integer.parseInt(move);
                break;
            } else {
                System.out.println(INVALID_INPUT);
            }
        }

        return new Position(number);
    }

    @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
    public static boolean askPlayAgainWithHistory() {
        String response;

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        System.out.println("Or type (h) for history.");
        response = SCANNER.nextLine();

        while(! ("y".equals(response) || "n".equals(response) || "h".equals(response))) {
            System.out.println(INVALID_INPUT);
            response = SCANNER.nextLine();
        }

        if("h".equals(response)) {
            MatchHistory matchHistory = new MatchHistory();
            matchHistory.printMatchHistory();
            return askPlayAgain();
        }

        return "y".equals(response);
    }

    public static boolean askPlayAgain() {
        String response;

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        response = SCANNER.nextLine();

        while(! ("y".equals(response) || "n".equals(response))) {
            System.out.println(INVALID_INPUT);
            response = SCANNER.nextLine();
        }
        return "y".equals(response);
    }

    public static void askForDifficulty() {
        String response;
        System.out.println("What difficulty would you like to play?   Easy, Medium, impossible?   (Type e/m/i)");
        response = SCANNER.nextLine();

        while(! ("e".equals(response) || "m".equals(response) || "i".equals(response))) {
            System.out.println(INVALID_INPUT);
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


    private static boolean isInteger(String input) {

        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
            System.out.println(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }


}
