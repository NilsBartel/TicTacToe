import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {

    private final static int PASSWORD_MIN_LENGTH = 8;
    private final static int PASSWORD_MAX_LENGTH = 32;



    public static boolean isPasswordValid(String password) {

        if(password.length() >= PASSWORD_MIN_LENGTH && password.length() < PASSWORD_MAX_LENGTH) {

            Pattern lowerCaseLetter = Pattern.compile("[a-zäüöß]");
            Pattern upperCaseLetter = Pattern.compile("[A-ZÄÖÜ]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~\\-.,\"^°'´`]");


            Matcher hasLetter = lowerCaseLetter.matcher(password);
            Matcher hasUpperLetter = upperCaseLetter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasUpperLetter.find() && hasDigit.find() && hasSpecial.find();
        }
        return false;
    }


    public static boolean checkPassword(String inputPassword, String hashedPassword) {

        return HashService.verify(inputPassword, hashedPassword);
    }


    public static boolean resetPassword(String userName, Users users, File file) {

        if (checkSecurityQuestions(userName, users)) {
            String newPassword = PlayerInput.getInstance().crateNewPassword();
            User user = users.getUser(userName);
            user.setPassword(HashService.hash(newPassword));
            FileWriteRead.getInstance().writeToUserFile(file, users);
            return true;

        } else {
            LogInOutput.getInstance().failedReset();
            return false;
        }
    }

    private static Boolean checkSecurityQuestions(String userName, Users users) {

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
