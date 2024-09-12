import java.util.ArrayList;
import java.util.Arrays;

public class Match {
    // game loop

    private final Board board;
    private MatchStatus status;
    public static final char PLAYER_SYMBOL = 'o';
    public static final char COMPUTER_SYMBOL = 'x';
    public static final char EMPTY_SYMBOL = ' ';
    private boolean isPlayerTurn;
    private char currentSymbol;


    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
    }


    public void play(){

        this.status = MatchStatus.RUNNING;


        int moveCounter = 0;
//        board.getRows().get(1).getFields().get(2).setSymbol('S');
//        board.getRows().get(1).getFields().get(0).setSymbol('S');

        if(isPlayerTurn){
            currentSymbol = PLAYER_SYMBOL;
        } else{
            currentSymbol = COMPUTER_SYMBOL;
        }

        ArrayList<Integer> list = new ArrayList<>();

        //board.print();
        //System.out.println();
        while (true){

            int move;

            if(isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;
                //move = PlayerInput.askForMove(board);
                move = ComputerMoveService.randomMove(board);

            } else{
                currentSymbol = COMPUTER_SYMBOL;
                move = ComputerMoveService.betterComputerMove(board);
            }

            list.add(move);


            Position position = new Position(move);
            int row = position.getRow();
            int column = position.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(currentSymbol);

            //System.out.println();
            //board.print();



            if (Winner.thereIsWinner(board, position, currentSymbol)) {

                if (currentSymbol == COMPUTER_SYMBOL){
                    this.status = MatchStatus.COMPUTER_WON;
                } else{
                    this.status = MatchStatus.PLAYER_WON;
                    System.out.println(list);
                }
                break;
            }

            moveCounter++;
            if (moveCounter > 9-1) {
                this.status = MatchStatus.DRAW;
                break;
            }

            isPlayerTurn = !isPlayerTurn;

        }
    }



    public Board getBoard() {
        return board;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public boolean getIsPlayerTurn() {
        return isPlayerTurn;
    }
}
