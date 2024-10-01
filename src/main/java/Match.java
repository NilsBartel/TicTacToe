import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;

public class Match {

    private final Board board;
    private MatchStatus status;
    public static final char PLAYER_SYMBOL = 'o';
    public static final char COMPUTER_SYMBOL = 'x';
    public static final char EMPTY_SYMBOL = ' ';
    private boolean isPlayerTurn;
    private long startTime;
    private long endTime;



    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
    }


    public void play(int mediumDifficulty) {

        this.status = MatchStatus.RUNNING;
        startTime = System.currentTimeMillis();

        int moveCounter = 0;
        setPlayerTurn();

        board.print();
        System.out.println();
        while (true){
            char currentSymbol;

            Position position;
            if(isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;
                position = PlayerInput.getInstance().askForMove(board);
            } else{
                currentSymbol = COMPUTER_SYMBOL;
                position = Difficulty.returnMove(board, mediumDifficulty);
            }

            board.setSymbol(position.getRow(), position.getColumn(), currentSymbol);

            System.out.println();
            board.print();

            moveCounter++;
            if(isGameOver(board, position, currentSymbol, moveCounter)){
                break;
            }

            isPlayerTurn = !isPlayerTurn;
        }


        endTime = System.currentTimeMillis();
        writeToHistoryFile();
    }



    private boolean isGameOver(Board board, Position position, char currentSymbol, int moveCounter) {

        if (Winner.thereIsWinner(board, position, currentSymbol)) {
            if (currentSymbol == COMPUTER_SYMBOL){
                this.status = MatchStatus.COMPUTER_WON;
            } else{
                this.status = MatchStatus.PLAYER_WON;
            }
            return true;
        }

        if (moveCounter > 9-1) {
            this.status = MatchStatus.DRAW;
            return true;
        }

        return false;
    }

    private void writeToHistoryFile() {
        File file = Main.FILE_MATCH_HISTORY;

        MatchHistory history = new MatchHistory();
        if (file.exists() && file.length() != 0 && FileWriteRead.readFromHistoryFile(file) != null) {
            history = FileWriteRead.readFromHistoryFile(file);
        }
        history.addMatch(this);
        FileWriteRead.getInstance().writeToHistoryFile(file, history);
    }


    private void setPlayerTurn() {

        if (Main.FILE_SCORE.exists() && Main.FILE_SCORE.length() != 0 && FileWriteRead.getInstance().readFile(Main.FILE_SCORE) != null) {
            Score score = FileWriteRead.getInstance().readFile(Main.FILE_SCORE);
            this.isPlayerTurn = score.getRoundCounter() % 2 == 0;
        } else {
            this.isPlayerTurn = false;
        }
    }

    public void printBoard() {
        board.print();
    }

    public MatchStatus getStatus() {
        return status;
    }

    public static char getOpponentsSymbol(char symbol) {
        if (symbol == COMPUTER_SYMBOL){
            return PLAYER_SYMBOL;
        } else {
            return COMPUTER_SYMBOL;
        }
    }

    public Board getBoard() {
        return board;
    }

    @JsonIgnore
    public boolean isIsPlayerTurn() {
        return isPlayerTurn;
    }

    public void setIsPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
