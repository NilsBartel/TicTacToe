public class LogIn {

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_TRIES = 3;




    public static String validateUser(String userName, Users user) {
        String password;

        if (user.userNameExists(userName)) {
            password = PlayerInput.getInstance().askForPassword();
            if (!checkPassword(userName, password, user)) {
                System.out.println();
                System.out.println();
                System.out.println("Too many invalid tries!");
                return null;
            }

        } else {
            userName = createUser(user);
            System.out.println("created new user: " + userName);
        }

        return userName;
    }









    private static String createUser(Users user) {
        String userName;
        System.out.println("No user found, creating new account");

        do {
            userName = PlayerInput.getInstance().askForNewUserName();
            if (!user.userNameExists(userName)) {
                String password = PlayerInput.getInstance().askForNewPassword();
                user.addUser(userName,  HashService.hash(password));
                break;
            }
        } while (true);


        FileWriteRead.getInstance().writeToUserFile(FileService.getInstance().getFileUserData(), user);
        return userName;
    }


    private static boolean checkPassword(String userName, String password, Users user) {

        int counter = PASSWORD_MAX_TRIES;
        String hashedPassword = user.getPassword(userName);



        while (HashService.verify(password, hashedPassword)) { //!user.validatePassword(userName, password)
            counter--;
            if (counter == 0) {
                return false;
            }
            System.out.println("Password does not match, please try again! (tries left: " + counter + ")");
            password = PlayerInput.getInstance().askForPassword();
        }
        return true;
    }






}
