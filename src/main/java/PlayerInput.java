import java.util.Scanner;

public final class PlayerInput {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INVALID_INPUT = "This is not a valid input! Please try again.";
    private static PlayerInput instance;

    private PlayerInput() {
    }
    public static PlayerInput getInstance() {
        if (instance == null) {
            instance = new PlayerInput();
        }
        return instance;
    }



    private String myScanner() {
        return SCANNER.nextLine();
    }

    public Position askForMove(Board board) {
        String move;
        int number;
        while(true){
            System.out.println("Please pick a field (1-9)");
            move = myScanner();

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
    public boolean askPlayAgain() {
        String response;

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        System.out.println("type (h) for history.");
        System.out.println("type (a) to analyse Match History.");
        response = myScanner();

        while(! ("y".equals(response) || "n".equals(response) || "h".equals(response) || "a".equals(response))) {
            System.out.println(INVALID_INPUT);
            response = myScanner();
        }

        if("h".equals(response)) {
            MatchHistory matchHistory = MatchHistory.fromFile(FileService.getInstance().getFileMatchHistory());
            matchHistory.printMatchHistory();
            return askPlayAgain();
        }

        if("a".equals(response)) {
            MatchHistory matchHistory =  MatchHistory.fromFile(FileService.getInstance().getFileMatchHistory());
            PrintService.getInstance().printAnalysedWinPositions(AnalyseService.getInstance().findBestWinningLine(matchHistory));
            return askPlayAgain();
        }


        return "y".equals(response);
    }

    public DifficultyState askForDifficulty() {
        String response;
        System.out.println();
        System.out.println("What difficulty would you like to play?   Easy, Medium, impossible?   (Type e/m/i)");
        response = myScanner();

        while(! ("e".equals(response) || "m".equals(response) || "i".equals(response))) {
            System.out.println(INVALID_INPUT);
            response = myScanner();
        }

        return switch (response) {
            case "e" -> DifficultyState.EASY;
            case "m" -> DifficultyState.MEDIUM;
            case "i" -> DifficultyState.IMPOSSIBLE;
            default -> throw new IllegalStateException("Unexpected value: " + response);
        };
    }

    @SuppressWarnings("PMD.UnusedLocalVariable")
    public boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String askForName(){
        String response;
        System.out.println();
        System.out.println("What is your name?");
        response = myScanner();

        return response;
    }

    public String askForUsername(){
        String response;
        System.out.println();
        System.out.println("What is your username?");
        response = myScanner();
        return response;
    }

    public String askForPassword(){
        String response;
        System.out.println();
        System.out.println("What is your password?");
        response = myScanner();
        return response;
    }

    public String askForNewUserName() {
        String response;
        System.out.println();
        System.out.println(" Please choose a Username.");
        response = myScanner();
        while (response.length() < LogIn.userNameMinLength){
            System.out.println("User name too short, please try again.");
            response = myScanner();
        }
        return response;
    }

    public String askForNewPassword() {
        String response;
        System.out.println();
        System.out.println("Please choose a Password.");
        response = myScanner();
        while (response.length() < LogIn.passwordMinLength){
            System.out.println("Password too short, please try again.");
            response = myScanner();
        }
        return response;
    }


}
