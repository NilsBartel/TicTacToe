import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordUtil {

    private final static int PASSWORD_MIN_LENGTH = 8;
    private final static int PASSWORD_MAX_LENGTH = 32;

    private PasswordUtil() {}



    public static boolean isPasswordValid(String password) {

        if(password.length() >= PASSWORD_MIN_LENGTH && password.length() < PASSWORD_MAX_LENGTH) {

            Pattern lowerCaseLetterPattern = Pattern.compile("[a-zäüöß]");
            Pattern upperCaseLetterPattern = Pattern.compile("[A-ZÄÖÜ]");
            Pattern digitPattern = Pattern.compile("[0-9]");
            Pattern specialPattern = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~\\-.,\"^°'´`]");


            Matcher lowerLetterMatcher = lowerCaseLetterPattern.matcher(password);
            Matcher upperLetterMatcher = upperCaseLetterPattern.matcher(password);
            Matcher digitMatcher = digitPattern.matcher(password);
            Matcher specialMatcher = specialPattern.matcher(password);

            return lowerLetterMatcher.find() && upperLetterMatcher.find() && digitMatcher.find() && specialMatcher.find();
        }
        return false;
    }


    public static boolean checkPassword(String inputPassword, String hashedPassword) {

        return HashService.verify(inputPassword, hashedPassword);
    }


    public static boolean resetPassword(String userName, Users users, File file, PlayerInput playerInput, LogInOutput logInOutput) {

        if (checkSecurityQuestions(userName, users, playerInput, logInOutput)) {
            String newPassword = playerInput.crateNewPassword();
            User user = users.getUser(userName);
            user.setPassword(HashService.hash(newPassword));
            FileWriteRead.getInstance().writeToUserFile(file, users);
            return true;

        } else {
            logInOutput.failedReset();
            return false;
        }
    }

    private static Boolean checkSecurityQuestions(String userName, Users users, PlayerInput playerInput, LogInOutput logInOutput) {

        boolean bool1 = false;
        boolean bool2 = false;
        User user = users.getUser(userName);
        String userAnswer1 = playerInput.askRecoveryQuestion1();
        if (HashService.verify(userAnswer1, user.getAnswer1())) {
            logInOutput.correct();
            bool1 = true;
        } else {
            logInOutput.incorrect();
        }

        String userAnswer2 = playerInput.askRecoveryQuestion2();
        if (HashService.verify(userAnswer2, user.getAnswer2())) {
            logInOutput.correct();
            bool2 = true;
        } else {
            logInOutput.incorrect();
        }
        return bool1 && bool2;
    }






}
