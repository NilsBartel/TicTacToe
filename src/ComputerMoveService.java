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

    public static int betterComputerMove(Board board) {


        // checks if computer can win with the next move
//        for (int i = 1; i < fieldSize*fieldSize+1; i++) {
//            int[] coordinates = returnCoordinates(i);
//
//            if(spielFeld[coordinates[0]][coordinates[1]] != leeresFeld) continue;
//
//            spielFeld[coordinates[0]][coordinates[1]] = computerChar;
//            if (checkForWinner(coordinates, false)){
//                System.out.println("not random move");
//                spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
//                return i;
//            }
//            spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
//        }


        // checks if computer can lose with the next move
//        for (int i = 1; i < fieldSize*fieldSize+1; i++) {
//            int[] coordinates = returnCoordinates(i);
//
//            if(spielFeld[coordinates[0]][coordinates[1]] != leeresFeld) continue;
//
//            spielFeld[coordinates[0]][coordinates[1]] = playerChar;
//            if (checkForWinner(coordinates, false)){
//                System.out.println("not random move");
//                spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
//                return i;
//            }
//            spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
//        }







        return 0;
    }


}
