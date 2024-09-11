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


        //checks if computer can win with the next move
        int bestMove = checkWhereSymbolCanWin(board, Match.COMPUTER_SYMBOL);
        if( bestMove != -1) {
            System.out.println("used win");
            return bestMove;
        }


        // checks if computer can lose with the next move
        bestMove = checkWhereSymbolCanWin(board, Match.PLAYER_SYMBOL);
        if( bestMove != -1) {
            System.out.println("prevented win");
            return bestMove;
        }


        //find a fork
        bestMove = findFork(board, Match.COMPUTER_SYMBOL);
        if( bestMove != -1) {
            System.out.println("used a fork");
            return bestMove;
        }

        //prevent a fork
        bestMove = findFork(board, Match.PLAYER_SYMBOL);
        if( bestMove != -1) {
            System.out.println("prevent a fork");
            return bestMove;
        }








        return randomMove(board);
    }


    private static int checkWhereSymbolCanWin(Board board, char symbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            if (Winner.thereIsWinner(board, position, symbol)){
                System.out.println("not random move");
                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                return i;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
        }


        return -1;
    }

    private static int findFork(Board board, char symbol) {
        // check for a move where I have 2 winning moves the next round (and pick that move)
        for (int i = 1; i < 9+1; i++) {
            int counter = 0;

            //System.out.print(1);
            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();
            board.getRows().get(row).getFields().get(column).setSymbol(symbol);


            //checks the second level
            if (checkForTwoWins(board, symbol)) return i;

            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
        }

        return -1;
    }

    private static boolean checkForTwoWins(Board board, char symbol) {

        int counter = 0;

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            if (Winner.thereIsWinner(board, position, symbol)){

                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                counter++;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);

            if (counter == 2){
                return true;
            }
        }



        return false;
    }


}
