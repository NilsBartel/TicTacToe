public class LogIn {

    public static final int userNameMinLength = 3;
    public static final int passwordMinLength = 4;




    public static String validateUser(String userName, User user) {
        String password;

        if (user.validateUserName(userName)) {
            password = PlayerInput.getInstance().askForPassword();
            checkPassword(userName, password, user);

        } else {
            userName = createUser(user);
        }

        return userName;
    }









    private static String createUser(User user) {
        String userName;
        System.out.println("No user found, creating new account");

        do {
            userName = PlayerInput.getInstance().askForNewUserName();
            if (!user.validateUserName(userName)) {
                String password = PlayerInput.getInstance().askForNewPassword();
                user.addUser(userName, password);
                break;
            }
        } while (true);


        FileWriteRead.getInstance().writeToUserFile(FileService.getInstance().getFileUserData(), user);
        return userName;
    }


    private static void checkPassword(String userName, String password, User user) {

        while (!user.validatePassword(userName, password)) {
            System.out.println("Password does not match, please try again");
            password = PlayerInput.getInstance().askForPassword();
        }

    }






}
