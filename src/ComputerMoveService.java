import java.util.Random;

public class ComputerMoveService {










    public static int randomMove(Board board) {

        Random rand = new Random();

        /*int n;
        n = rand.nextInt(8) + 1;*/

        int n;

        do{
            n = rand.nextInt(8) + 1;
        } while (!board.isValid(n));

        System.out.println("random move");



        /*do{
            n = rand.nextInt(8) + 1;
            coordinaten = returnCoordinates(n);
        }   while (spielFeld[coordinaten[0]][coordinaten[1]] != leeresFeld);*/

        return n;
    }

}
