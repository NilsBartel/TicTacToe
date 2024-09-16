import java.util.ArrayList;
import java.util.Random;

public class ComputerMoveService {



    public static Position randomMove(Board board) {

        Random rand = new Random();
        int n;
        do{
            n = rand.nextInt(9) + 1;
        } while (!board.isValid(n));

        //System.out.println("random move");
        return new Position(n);
    }

    public static Position betterComputerMove(Board board) {


        //checks if computer can win with the next move
        Position bestMove = returnWhereSymbolCanWin(board, Match.COMPUTER_SYMBOL);
        if(bestMove != null) {
            //System.out.println("used win");
            return bestMove;
        }

        // checks if computer can lose with the next move
        bestMove = returnWhereSymbolCanWin(board, Match.PLAYER_SYMBOL);
        if(bestMove != null) {
            //System.out.println("prevented win");
            return bestMove;
        }

        //find a fork
        ArrayList<Position> forks = new ArrayList<>();
        forks = findFork(board, Match.COMPUTER_SYMBOL);
        if(forks.size() == 1) {
            //System.out.println("used fork");
            return forks.getFirst();
        }
        //System.out.println("no fork found");

        if(!board.isEmpty()){
            if(board.isValid(5)) return new Position(5);
        }


        // COMPUTER:
        // check for 2 forks
        //      go through all the moves (as computer) and place a symbol
        //           check where would player's best move be? (use existing test) (and place a symbol (player))
        //                is there still a fork for the player?
        //                      yes:              no:
        //                      go next           return (the loop value)


        


















        // if we find 2 forks for the player

        // go through every field

        // place a computer mark

        // check if there is still a chance for the player to fork





        //prevent 2 forks then,
        //prevent 1 fork
        
        ArrayList<Position> forksToWin = new ArrayList<>();
        
         forksToWin = findFork(board, Match.PLAYER_SYMBOL);

        // found 2 forks
        if (forksToWin.size() >= 2) {
            //System.out.println("found two forks");
//            int defendMove = 1;
//
              Position defendMove = makeOpponentDefend(board, Match.COMPUTER_SYMBOL);
//
//            // do I need a loop?
//
//            defendMove = makeOpponentDefend(board, Match.COMPUTER_SYMBOL, defendMove);
//            // if "defendMove" results in opponent winning or creating another fork:  try again
//
//            //System.out.println("test3");
//            if(returnWhereSymbolCanWin(board, Match.PLAYER_SYMBOL) != -1) {
//                System.out.println("test2");
//                if(findFork(board, Match.PLAYER_SYMBOL) != -2 && findFork(board, Match.PLAYER_SYMBOL) != -1) {
//                    System.out.println("test");
//                    return  defendMove;
//                }
//            }
//
//
//

            if(defendMove != null) {
                //System.out.println("made opponent defend");
                return defendMove;
            }
            // cant make opponent defend, use the move that creates the double fork
            return forksToWin.getFirst();

        }
        //System.out.println("no forks");


        //found a fork for the opponent and stopping it by placing the mark there
        if (forks.size() == 1) {
            //System.out.println("prevented single fork");
            return forksToWin.getFirst();
        }
//        if (foundFork != -1 && foundFork != -2) {
//            return foundFork;
//        }
















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
                board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);
                return position;
            }

            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.EMPTY_SYMBOL);

        }
        return null;
    }



    // -1 if no fork found, -2 if 2 forks found, or the move with which the fork is being created
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

    
//    private static int returnMoveWhereTheNextCouldWin(Board board, char symbol) {
//
//        for (int i = 1; i < 9+1; i++) {
//
//            if(!board.isValid(i)) continue;
//
//            Position position = new Position(i);
//            int row = position.getRow();
//            int column = position.getColumn();
//
//            board.getRows().get(row).getFields().get(column).setSymbol(symbol);
//
//            int bestMove = returnWhereSymbolCanWin(board, symbol);
//            if(bestMove != -1) {
//                board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
//                return bestMove;
//            }
//            board.getRows().get(row).getFields().get(column).setSymbol(Match.EMPTY_SYMBOL);
//        }
//
//        return -1;
//    }


}
