import java.util.Random;

public class ComputerMoveService {










    public static int randomMove(Board board) {

        Random rand = new Random();
        int n;
        do{
            n = rand.nextInt(9) + 1;
        } while (!board.isValid(n));

        System.out.println("random move");
        return n;
    }

    public static int betterComputerMove(Board board) {


        //checks if computer can win with the next move
        int bestMove = returnWhereSymbolCanWin(board, Match.COMPUTER_SYMBOL);
        if( bestMove != -1) {
            System.out.println("used win");
            return bestMove;
        }


        // checks if computer can lose with the next move
        bestMove = returnWhereSymbolCanWin(board, Match.PLAYER_SYMBOL);
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
        int foundFork = findFork(board, Match.PLAYER_SYMBOL);
        if(foundFork != -1) { //found a fork for the opponent
            System.out.println("prevented a fork");
            return returnMoveWhereTheNextCouldWin(board, Match.COMPUTER_SYMBOL);
        }


        //plays the middle
        if(board.isValid(5)) return 5;


        //Opposite corner


        // play a corner
        if(board.isValid(1)) return 1;
        if(board.isValid(3)) return 3;
        if(board.isValid(7)) return 7;
        if(board.isValid(9)) return 9;





        return randomMove(board);
    }

    private static int returnWhereSymbolCanWin(Board board, char symbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            if (Winner.thereIsWinner(board, position, symbol)){
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

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();
            board.getRows().get(row).getFields().get(column).setSymbol(symbol);


            //checks the second level
            if (checkForTwoWins(board, symbol)) {
                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                return i;
            }

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
    
    private static int returnMoveWhereTheNextCouldWin(Board board, char symbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);

            int bestMove = returnWhereSymbolCanWin(board, symbol);
            if(bestMove != -1) {
                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                return bestMove;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
        }

        return -1;
    }


}
