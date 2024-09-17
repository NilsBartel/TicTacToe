import java.util.ArrayList;
import java.util.Random;

public class ComputerMoveService {





    public static Position randomMove(Board board) {

        Random rand = new Random();
        int n;
        do{
            n = rand.nextInt(9) + 1;
        } while (!board.isValid(n));

        System.out.println("random move");
        return new Position(n);
    }

    public static Position impossibleComputerMove(Board board) {
        System.out.println("not random move");

        //checks if computer can win with the next move
        Position bestMove = returnWhereSymbolCanWin(board, Match.COMPUTER_SYMBOL);
        if(bestMove != null) {
            return bestMove;
        }

        // checks if computer can lose with the next move
        bestMove = returnWhereSymbolCanWin(board, Match.PLAYER_SYMBOL);
        if(bestMove != null) {
            return bestMove;
        }

        //find a fork
        ArrayList<Position> forks = new ArrayList<>();
        forks = findFork(board, Match.COMPUTER_SYMBOL);
        if(forks.size() == 1) {
            return forks.getFirst();
        }

        if(!board.isEmpty()){
            if(board.isValid(5)) return new Position(5);
        }


        // find 2 forks for opponent
        ArrayList<Position> forksToWin;
        forksToWin = findFork(board, Match.PLAYER_SYMBOL);
        if (forksToWin.size() >= 2) {
            Position defendMove = makeOpponentDefend(board, Match.COMPUTER_SYMBOL);

            if(defendMove != null) {
                return defendMove;
            }
            // cant make opponent defend, use the move that creates the double fork
            return forksToWin.getFirst();
        }


        //found a fork for the opponent and stopping it by placing the mark there
        if (forks.size() == 1) {
            return forksToWin.getFirst();
        }



        //plays the middle (if it's not the first move)
        if(!board.isEmpty()){
            if(board.isValid(5)) return new Position(5);
        }

        // play a corner
        if(board.isValid(1)) return new Position(1);
        if(board.isValid(3)) return new Position(3);
        if(board.isValid(7)) return new Position(7);
        if(board.isValid(9)) return new Position(9);



        return randomMove(board);
    }

    public static Position mediumComputerMove(Board board) {
        Random rand = new Random();

        int num = rand.nextInt(100);
        if(num < Main.MEDIUM_DIFFICULTY_PERCENTAGE){
            return randomMove(board);
        } else return impossibleComputerMove(board);
    }

    public static Position easyComputerMove(Board board) {
        return randomMove(board);
    }


    private static Position returnWhereSymbolCanWin(Board board, char symbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) continue;

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            if (Winner.thereIsWinner(board, position, symbol)){
                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                return position;
            }
            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
        }


        return null;
    }

    // find a move with which we could win next
    private static Position makeOpponentDefend(Board board, char symbol) {
        for (int first = 1; first < 9+1; first++) {
            Position position = new Position(first);

            if(!board.isValid(first)) continue;

            if (Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT.contains(position) || Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT.contains(position)) {
                continue;
            }

            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(symbol);


            if(returnWhereSymbolCanWin(board, symbol) != null){

                // check if there is still a chance to get a fork
                Position bestMove = returnWhereSymbolCanWin(board, symbol);
                if(bestMove != null) {
                    board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.getOpponentsSymbol(symbol));

                    if (checkForTwoWins(board, Match.getOpponentsSymbol(symbol))){
                        board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                        board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                        return bestMove;
                    }
                    board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.EMPTY_SYMBOL);

                }

                board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                return position;
            }

            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);

        }
        return null;
    }

    private static ArrayList<Position> findFork(Board board, char symbol) {
        ArrayList<Position> positions = new ArrayList<>();

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
                //System.out.println(position.getIndex());
                positions.add(position);
                // add random position to have two in the list
                positions.add(position);
            }

            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
        }

        return positions;
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
