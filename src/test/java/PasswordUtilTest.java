import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {




    private static Stream<Arguments> generatePasswordsForTest() {
        return Stream.of(
                Arguments.of("1!eeeQWrtt", true),
                Arguments.of("1234567!!Qü", true),
                Arguments.of("1234567!!Qä", true),
                Arguments.of("1234567!!Qö", true),
                Arguments.of("1234567!!Qß", true),
                Arguments.of("1234567!!aÜ", true),
                Arguments.of("1234567!!aÄ", true),
                Arguments.of("1234567!!aÖ", true),

                Arguments.of("", false),
                Arguments.of("1dQ!", false),
                Arguments.of("hallo123456", false),
                Arguments.of("hallo.....()(", false),
                Arguments.of("hallo123..-=", false),
                Arguments.of("HALLO11223..-?", false),
                Arguments.of("123.....jjjjjjjjj", false),
                Arguments.of("aaa", false),
                Arguments.of("AAA", false),
                Arguments.of("...", false),
                Arguments.of("111", false),
                Arguments.of("aaaaaaaaaaaaaaaaaasasasasasasasasasasasasasasasasasasa", false),
                Arguments.of("aaaaaaaaaaaaaaaaaasasasasasasasas.AA(()11122sasasasasasasasasasa", false)
        );
    }

    @ParameterizedTest
    @MethodSource("generatePasswordsForTest")
    void computerMove(String password, boolean expected) {

        boolean bool = PasswordUtil.isPasswordValid(password);

        assertEquals(bool, expected);
    }

    @Test
    void checkPasswordTestTrue(){
        String hashedPassword = HashService.hash("myNewPassword12_:");

        boolean bool = PasswordUtil.checkPassword("myNewPassword12_:", hashedPassword);

        assertTrue(bool);
    }

    @Test
    void checkPasswordTestFalse(){
        String hashedPassword = HashService.hash("not my password");

        boolean bool = PasswordUtil.checkPassword("myNewPassword12_:", hashedPassword);

        assertFalse(bool);
    }


    @Test
    void resetPasswordTestTrue(){
        File file = new File("src/test/java/testUserData.json");
        Users users = FileWriteRead.getInstance().readFromUserFile(file);
        Users tempUsers = users;
        String userName = "nils";

       boolean bool = PasswordUtil.resetPassword(userName, tempUsers, file);

       assertTrue(bool);





    }






}