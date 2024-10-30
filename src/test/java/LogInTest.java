import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogInTest {


    @Test
    void userNameExistsTest(){
        Users users = FileWriteRead.getInstance().readFromUserFile(new File("src/test/java/testUserData.json"));
        String userName = "nils";

        boolean bool = LogIn.getInstance().userNameExists(userName, users);

        assertTrue(bool);
    }

    @Test
    void userNameNotExistsTest(){
        Users users = FileWriteRead.getInstance().readFromUserFile(new File("src/test/java/testUserData.json"));
        String userName = "Bernd";

        boolean bool = LogIn.getInstance().userNameExists(userName, users);

        assertFalse(bool);
    }



    @Test
    void loginUserTestTrue(){
        PlayerInput mockPlayerInput = mock(PlayerInput.class);
        LogInOutput mockLogInOutput = mock(LogInOutput.class);

        Users users = FileWriteRead.getInstance().readFromUserFile(new File("src/test/java/testUserData.json"));
        String userName = "nils";
        String password = "nilspassword";
        when(mockPlayerInput.askForPassword()).thenReturn(password);


        boolean bool = LogIn.getInstance().logInUser(users, userName, mockPlayerInput, mockLogInOutput);

        assertTrue(bool);
    }

    @Test
    void loginUserTestFalse(){
        PlayerInput mockPlayerInput = mock(PlayerInput.class);
        LogInOutput mockLogInOutput = mock(LogInOutput.class);

        Users users = FileWriteRead.getInstance().readFromUserFile(new File("src/test/java/testUserData.json"));
        String userName = "wrong userName";

        boolean bool = LogIn.getInstance().logInUser(users, userName, mockPlayerInput, mockLogInOutput);

        assertFalse(bool);
    }













}