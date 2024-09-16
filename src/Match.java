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

        if(isPlayerTurn){
            currentSymbol = PLAYER_SYMBOL;
        } else{
            currentSymbol = COMPUTER_SYMBOL;
        }

        ArrayList<Position> list = new ArrayList<>();

        //board.print();
        //System.out.println();
        while (true){

            Position position;

            if(isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;
                position = ComputerMoveService.randomMove(board);
                //position = PlayerInput.askForMove(board);
                //System.out.println("player: " + move);

            } else{
                currentSymbol = COMPUTER_SYMBOL;
                position = ComputerMoveService.betterComputerMove(board);
                //System.out.println("computer: " + move);
            }

            list.add(position);


            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(currentSymbol);

            //System.out.println();
            //board.print();



            if (Winner.thereIsWinner(board, position, currentSymbol)) {
                if (currentSymbol == COMPUTER_SYMBOL){
                    this.status = MatchStatus.COMPUTER_WON;
                } else{
                    this.status = MatchStatus.PLAYER_WON;

                    for(Position p : list){
                        System.out.print(p.getIndex() + ", ");
                    }
                    System.out.println();
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

    public static char getOpponentsSymbol(char symbol) {
        if (symbol == COMPUTER_SYMBOL){
            return PLAYER_SYMBOL;
        } else return COMPUTER_SYMBOL;
    }
}
