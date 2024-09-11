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

    public static int betterComputerMove(Board board, char computerSymbol, char playerSymbol, char emptySymbol) {


        // checks if computer can win with the next move
        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(computerSymbol);
            if (Winner.thereIsWinner(board, position, computerSymbol)){
                System.out.println("not random move");
                board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
                return i;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
        }




        // checks if computer can lose with the next move
        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(playerSymbol);
            if (Winner.thereIsWinner(board, position, playerSymbol)){
                System.out.println("not random move");
                board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
                return i;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
        }













        return randomMove(board);
    }


    private static int checkWhereSymbolCanWin(Board board, char symbol, char emptySymbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            if (Winner.thereIsWinner(board, position, symbol)){
                System.out.println("not random move");
                board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
                return i;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(emptySymbol);
        }



        return -1;
    }


}
