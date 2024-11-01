
public final class LogIn {

    public static final int USERNAME_MIN_LENGTH = 3;
    private static LogIn instance;
    private LogIn() {}
    public static LogIn getInstance() {
        if (instance == null) {
            instance = new LogIn();
        }
        return instance;
    }


    public boolean logInUser(Users users, String userName, PlayerInput playerInput) {
        String password;
        password = playerInput.askForPassword();

        if(userNameExists(userName, users)){
            return PasswordUtil.checkPassword(password, users.getPassword(userName));
        } else {
            return false;
        }
    }


    public String createUser(Users users) {
        PlayerInput playerInput = PlayerInput.getInstance();
        String userName;

        while(true) {
            userName = playerInput.createNewUserName();
            if (!userNameExists(userName, users)) {
                String password = playerInput.crateNewPassword();
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

}
