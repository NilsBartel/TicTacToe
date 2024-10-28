import at.favre.lib.crypto.bcrypt.BCrypt;

public final class Main {


    private Main() {
    }

    public static void main(String[] args) {

        System.out.println("Welcome to TicTacToe!");
        StartGame startGame = new StartGame();
        startGame.start();

//        String salt = BCrypt.withDefaults().
//        String normalPassword = "password";
//        String hashedPassword = HashService.hash(normalPassword);
//        System.out.println(hashedPassword);
//
//        String normalPassword2 = "password";
//        String hashedPassword2 = HashService.hash(normalPassword2);
//        System.out.println(hashedPassword2);



//
//        System.out.println(HashService.verify(normalPassword, hashedPassword));
//


    }
}