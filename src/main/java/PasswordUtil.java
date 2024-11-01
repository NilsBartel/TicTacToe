import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {



    private final static int PASSWORD_MIN_LENGTH = 8;
    private final static int PASSWORD_MAX_LENGTH = 32;
    private final static int PASSWORD_MAX_TRIES = 3;





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









}
