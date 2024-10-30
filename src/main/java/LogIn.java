import java.io.File;

public final class LogIn {

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_TRIES = 3;
    private static LogIn instance;
    private LogIn() {}
    public static LogIn getInstance() {
        if (instance == null) {
            instance = new LogIn();
        }
        return instance;
    }


    public boolean logInUser(Users users, String userName, PlayerInput playerInput, LogInOutput logInOutput) {
        String password;

        if(userNameExists(userName, users)){

            password = playerInput.askForPassword();
            return checkPassword(userName, password, users) || resetPassword(userName, users);

        } else {
            logInOutput.printUserNotFound(userName);
            return false;
        }
    }


    public String createUser(Users users) {
        PlayerInput playerInput = PlayerInput.getInstance();
        String userName;

        while(true) {
            userName = playerInput.askForNewUserName();
            if (!userNameExists(userName, users)) {
                String password = playerInput.askForNewPassword();
                String question1 = playerInput.askRecoveryQuestion1();
                String question2 = playerInput.askRecoveryQuestion2();
                User user = new User(userName, HashService.hash(password), HashService.hash(question1), HashService.hash(question2));
                users.addUser1(user);
                break;
            }
        }

        FileWriteRead.getInstance().writeToUserFile(FileUtil.getInstance().getFileUserData(), users);
        LogInOutput.getInstance().createdNewUser(userName);
        return userName;
    }


    public boolean userNameExists(String userName, Users users) {
        for (User user : users.getUserList()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPassword(String userName, String password, Users users) {

        int counter = PASSWORD_MAX_TRIES;
        User user = users.getUser(userName);
        String tempPassword = password;

        while (!HashService.verify(tempPassword, user.getPassword())) {
            counter--;
            if (counter == 0) {
                LogInOutput.getInstance().toManyInvalidTries();
                return false;
            }
            LogInOutput.getInstance().triesLeft(counter);
            tempPassword = PlayerInput.getInstance().askForPassword();
        }
        return true;
    }

    private boolean resetPassword(String userName, Users users) {
        if(!PlayerInput.getInstance().askPasswordReset()){
            return false;
        }

       if (checkSecurityQuestions(userName, users)) {
           String newPassword = PlayerInput.getInstance().askForNewPassword();
           User user = users.getUser(userName);
           user.setPassword(HashService.hash(newPassword));
           FileWriteRead.getInstance().writeToUserFile(FileUtil.getInstance().getFileUserData(), users);
           return true;

       } else {
           LogInOutput.getInstance().failedReset();
           return false;
       }
    }

    private Boolean checkSecurityQuestions(String userName, Users users) {

        boolean bool1 = false;
        boolean bool2 = false;
        User user = users.getUser(userName);
        String userAnswer1 = PlayerInput.getInstance().askRecoveryQuestion1();
        if (HashService.verify(userAnswer1, user.getAnswer1())) {
            LogInOutput.getInstance().correct();
            bool1 = true;
        } else {
            LogInOutput.getInstance().incorrect();
        }

        String userAnswer2 = PlayerInput.getInstance().askRecoveryQuestion2();
        if (HashService.verify(userAnswer2, user.getAnswer2())) {
            LogInOutput.getInstance().correct();
            bool2 = true;
        } else {
            LogInOutput.getInstance().incorrect();
        }
        return bool1 && bool2;
    }





}
