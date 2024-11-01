import java.util.Scanner;

public class StartMenu {
    private static final Scanner SCANNER = new Scanner(System.in);


    private String myScanner() {
        return SCANNER.nextLine();
    }


    public void openMenu(){
        System.out.println();
        System.out.println("Type (a) to Log in.");
        System.out.println("Type (r) to reset password.");
        System.out.println("Type (b) to create a new account.");
        System.out.println("Type (q) to quit game.");

        String response = myScanner();
        while(! ("a".equals(response) || "r".equals(response) || "b".equals(response) || "q".equals(response))) {
            System.out.println("invalid input");
            response = myScanner();
        }

        String userName;
        Users users = FileWriteRead.getInstance().readFromUserFile(FileUtil.getInstance().getFileUserData());

        PlayerInput playerInput = PlayerInput.getInstance();

        switch(response) {
            case "a": {
                userName = playerInput.askForUserName();

                if(!LogIn.getInstance().logInUser(users, userName, playerInput)) {
                    System.out.println();
                    System.out.println("Wrong username or password");
                    openMenu();
                }
                break;
            }
            case "r": {
                userName = playerInput.askForUserName();
                while(!PasswordUtil.resetPassword(userName, users, FileUtil.getInstance().getFileUserData(), PlayerInput.getInstance(), LogInOutput.getInstance())) {
                    System.out.println("failed to reset password");
                }
                break;
            }
            case "b": {
                userName = LogIn.getInstance().createUser(users);
                break;
            }
            case "q": {
                System.exit(0);
            }
            default: {
                userName = null;
            }
        }

        FileUtil.getInstance().setFileName(userName);
        StartGame startGame = new StartGame();
        startGame.start();
    }


}
