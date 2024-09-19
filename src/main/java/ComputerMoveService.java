import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ComputerMoveService {
    private ComputerMoveService() {
    }

    public static Position randomMove(Board board) {

        Random rand = new Random();
        int randomNum;
        do{
            randomNum = rand.nextInt(9) + 1;
        } while (!board.isValid(randomNum));

        System.out.println("random move");
        return new Position(randomNum);
    }

    public static Position impossibleComputerMove(Board board) {
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
        List<Position> forks;
        forks = findFork(board, Match.COMPUTER_SYMBOL);
        if(!forks.isEmpty()) {
            return forks.getFirst();
        }


        if(!board.isEmpty() && board.isValid(5)){
            return new Position(5);
        }


        // find 2 forks for opponent
        List<Position> forksToWin;
        forksToWin = findFork(board, Match.PLAYER_SYMBOL);
        if (forksToWin.size() >= 2) {
            Position defendMove = makeOpponentDefend(board, Match.COMPUTER_SYMBOL);

            // stopping the fork by making opponent defend
            if(defendMove != null) {
                return defendMove;
            }
            // can't make opponent defend, use the move that creates the double fork
            return forksToWin.getFirst();
        }


        // play a corner
        if(board.isValid(1)) {
            return new Position(1);
        }
        if(board.isValid(3)) {
            return new Position(3);
        }
        if(board.isValid(7)) {
            return new Position(7);
        }
        if(board.isValid(9)) {
            return new Position(9);
        }



        return randomMove(board);
    }

    public static Position mediumComputerMove(Board board, int mediumDifficultyPercentage) {
        Random rand = new Random();

        int num = rand.nextInt(100);
        if(num < mediumDifficultyPercentage){
            return randomMove(board);
        } else {
            return impossibleComputerMove(board);
        }
    }

    public static Position easyComputerMove(Board board) {
        return randomMove(board);
    }


    private static Position returnWhereSymbolCanWin(Board board, char symbol) {

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) {
                continue;
            }

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            //board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            board.setSymbol(row, column, symbol);
            if (Winner.thereIsWinner(board, position, symbol)){
                //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                board.setSymbol(row, column, Match.EMPTY_SYMBOL);
                return position;
            }
            //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
            board.setSymbol(row, column, Match.EMPTY_SYMBOL);
        }


        return null;
    }

    // find a move with which we could win next
    private static Position makeOpponentDefend(Board board, char symbol) {
        for (int i = 1; i < 9+1; i++) {
            Position position = new Position(i);

            if(!board.isValid(i)) {
                continue;
            }

            if (Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT.contains(position) || Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT.contains(position)) {
                continue;
            }

            //board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(symbol);
            board.setSymbol(position.getRow(), position.getColumn(), symbol);


            if(returnWhereSymbolCanWin(board, symbol) != null){

                // check if there is still a chance to get a fork
                Position bestMove = returnWhereSymbolCanWin(board, symbol);
                if(bestMove != null) {
                    //board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.getOpponentsSymbol(symbol));
                    board.setSymbol(bestMove.getRow(), bestMove.getColumn(), Match.getOpponentsSymbol(symbol));

                    if (checkForTwoWins(board, Match.getOpponentsSymbol(symbol))){
                        //board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                        //board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                        board.setSymbol(position.getRow(), position.getColumn(), Match.EMPTY_SYMBOL);
                        board.setSymbol(bestMove.getRow(), bestMove.getColumn(), Match.EMPTY_SYMBOL);
                        return bestMove;
                    }
                    //board.getRows().get(bestMove.getRow()).getFields().get(bestMove.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                    board.setSymbol(bestMove.getRow(), bestMove.getColumn(), Match.EMPTY_SYMBOL);

                }

                //board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                board.setSymbol(position.getRow(), position.getColumn(), Match.EMPTY_SYMBOL);
                return position;
            }

            //board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
            board.setSymbol(position.getRow(), position.getColumn(), Match.EMPTY_SYMBOL);

        }
        return null;
    }

    private static List<Position> findFork(Board board, char symbol) {
        List<Position> positions = new ArrayList<>();

        // check for a move where I have 2 winning moves the next round (and pick that move)
        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) {
                continue;
            }

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();
            //board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            board.setSymbol(row, column, symbol);


            //checks the second level
            if (checkForTwoWins(board, symbol)) {
                //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                board.setSymbol(row, column, Match.EMPTY_SYMBOL);
                //System.out.println(position.getIndex());
                positions.add(position);
                // add random position to have two in the list
                positions.add(position);
            }

            //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
            board.setSymbol(row, column, Match.EMPTY_SYMBOL);

        }
        return positions;
    }

    private static boolean checkForTwoWins(Board board, char symbol) {

        int counter = 0;

        for (int i = 1; i < 9+1; i++) {

            if(!board.isValid(i)) {
                continue;
            }

            Position position = new Position(i);
            int row = position.getRow();
            int column = position.getColumn();

            //board.getRows().get(row).getFields().get(column).setSymbol(symbol);
            board.setSymbol(row, column, symbol);

            if (Winner.thereIsWinner(board, position, symbol)){

                //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
                board.setSymbol(row, column, Match.EMPTY_SYMBOL);

                counter++;
            }
            //board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
            board.setSymbol(row, column, Match.EMPTY_SYMBOL);

            if (counter == 2){
                return true;
            }
        }

        return false;
    }

}
