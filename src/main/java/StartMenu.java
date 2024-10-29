import java.util.Scanner;

public class StartMenu {
    private static final Scanner SCANNER = new Scanner(System.in);


    private String myScanner() {
        return SCANNER.nextLine();
    }


    public void openMenu(){
        System.out.println("Welcome to TicTacToe!");
        System.out.println();
        System.out.println("Type (a) to Log in.");
        System.out.println("Type (b) to create a new account.");
        System.out.println("Type (q) to quit game.");

        String response = myScanner();
        while(! ("a".equals(response) || "b".equals(response) || "q".equals(response))) {
            System.out.println("invalid input");
            response = myScanner();
        }


        Users users = FileWriteRead.getInstance().readFromUserFile(FileService.getInstance().getFileUserData());


        switch(response) {
            case "a": {
                String userName = PlayerInput.getInstance().askForUserName();
                FileService.getInstance().setFileName(userName);
                if(LogIn.logInUser(users, userName)) {
                    StartGame startGame = new StartGame();
                    startGame.start();
                } else {
                    openMenu();
                }
                break;
            }
            case "b": {
                String userName = LogIn.createUser(users);
                FileService.getInstance().setFileName(userName);
                StartGame startGame = new StartGame();
                startGame.start();
                break;
            }
            case "q": {
                System.exit(0);
                break;
            }
            default: {
            }
        }
    }








}
