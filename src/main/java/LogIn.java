public class LogIn {

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_TRIES = 3;





    public static boolean logInUser(Users users){
        String userName = PlayerInput.getInstance().askForUserName();
        String password;

        if(userNameExists(userName, users)){
            FileService.getInstance().setFileName(userName);
            password = PlayerInput.getInstance().askForPassword();
            if(checkPassword(userName, password, users)){
                return true;
            } else {
                return resetPassword(userName, users);
            }




        } else {
            userName = createUser(users);
            FileService.getInstance().setFileName(userName);
            return true;
        }



    }












    public static boolean validateUser(String userName, Users users) {
        String password;

        password = PlayerInput.getInstance().askForPassword();


        if (checkPassword(userName, password, users)){
            return true;
        }
        return resetPassword(userName, users);


//
//
//        if (!checkPassword(userName, password, users)) {
//
//            if (PlayerInput.getInstance().askPasswordReset()) {
//                if (resetPassword(userName, users)) {
//                    FileWriteRead.getInstance().writeToUserFile(FileService.getInstance().getFileUserData(), users);
//                    return userName;
//                }
//            }
//            return null;
//        }
//
//
//
//        return userName;
    }









    public static String createUser(Users users) {
        String userName;
        System.out.println();
        System.out.println("No user found, creating new account!");

        do {
            userName = PlayerInput.getInstance().askForNewUserName();
            if (!userNameExists(userName, users)) {
                String password = PlayerInput.getInstance().askForNewPassword();
                String question1 = PlayerInput.getInstance().askRecoveryQuestion1();
                String question2 = PlayerInput.getInstance().askRecoveryQuestion2();
                User user = new User(userName, HashService.hash(password), HashService.hash(question1), HashService.hash(question2));
                users.addUser1(user);
                break;
            }
        } while (true);


        FileWriteRead.getInstance().writeToUserFile(FileService.getInstance().getFileUserData(), users);
        System.out.println("created new user: " + userName);
        return userName;
    }


    private static boolean checkPassword(String userName, String password, Users users) {

        int counter = PASSWORD_MAX_TRIES;


        User user = users.getUser(userName);

        while (!HashService.verify(password, user.getPassword())) {
            counter--;
            if (counter == 0) {
                System.out.println();
                System.out.println();
                System.out.println("Too many invalid tries!");
                return false;
            }
            System.out.println("Password does not match, please try again! (tries left: " + counter + ")");
            password = PlayerInput.getInstance().askForPassword();
        }
        return true;
    }

    public static boolean userNameExists(String userName, Users users) {
        for (User user : users.getUsers()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }


    private static boolean resetPassword(String userName, Users users) {
        if(!PlayerInput.getInstance().askPasswordReset()){
            return false;
        }


       if (checkResetQuestions(userName, users)) {
           String newPassword = PlayerInput.getInstance().askForNewPassword();
           User user = users.getUser(userName);
           user.setPassword(HashService.hash(newPassword));
           FileWriteRead.getInstance().writeToUserFile(FileService.getInstance().getFileUserData(), users);
            return true;

       } else {
           System.out.println();
           System.out.println("Failed to reset password!");
           return false;
       }
    }

    private static Boolean checkResetQuestions(String userName, Users users) {


        String userAnswer1 = PlayerInput.getInstance().askRecoveryQuestion1();
        String userAnswer2 = PlayerInput.getInstance().askRecoveryQuestion2();
        boolean bool1 = false;
        boolean bool2 = false;

        User user = users.getUser(userName);

        if ((HashService.verify(userAnswer1, user.getAnswer1()))) {
            System.out.println("answer correct");
            bool1 = true;
        } else {
            System.out.println("answer incorrect");
        }
        if ((HashService.verify(userAnswer2, user.getAnswer2()))) {
            System.out.println("answer correct");
            bool2 = true;
        } else {
            System.out.println("answer incorrect");
        }
        return bool1 && bool2;
    }





}
