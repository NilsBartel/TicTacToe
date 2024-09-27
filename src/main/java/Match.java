import java.io.File;
import java.util.List;

public class Match {

    private final Board board;
    private MatchStatus status;
    public static final char PLAYER_SYMBOL = 'o';
    public static final char COMPUTER_SYMBOL = 'x';
    public static final char EMPTY_SYMBOL = ' ';
    private boolean isPlayerTurn;




    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
    }


    public void play(int mediumDifficulty) {

        this.status = MatchStatus.RUNNING;
        Time time = new Time();
        time.setStartTime();

        int moveCounter = 0;
        setPlayerTurn();

        board.print();
        System.out.println();
        while (true){
            char currentSymbol;

            Position position;
            if(isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;
                position = PlayerInput.askForMove(board);
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


        time.setEndTime();
        System.out.println(TimeUtility.convertToSeconds(List.of(time.getStartTime(), time.getEndTime())));

        writeToHistoryFile(board, time.getStartTime(), time.getEndTime(), Main.FILE_MATCH_HISTORY);

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

    private void writeToHistoryFile(Board board, Long firstTime, Long endTime, File file) {

        if (file.exists() && file.length() != 0 && JsonFileWriteRead.readHistoryFile(file) != null) {
            MatchHistory matchHistory = JsonFileWriteRead.readHistoryFile(file);
            matchHistory.addBoardToHistory(board);
            matchHistory.addTimeStampsToList(firstTime, endTime);
            JsonFileWriteRead.writeHistoryFile(file, matchHistory);
        } else {
            MatchHistory matchHistory = new MatchHistory();
            matchHistory.addBoardToHistory(board);
            matchHistory.addTimeStampsToList(firstTime, endTime);
            JsonFileWriteRead.writeHistoryFile(file, matchHistory);
        }

    }


    private void setPlayerTurn() {

        if (Main.FILE_SCORE.exists() && Main.FILE_SCORE.length() != 0 && JsonFileWriteRead.readFile(Main.FILE_SCORE) != null) {
            Score score = JsonFileWriteRead.readFile(Main.FILE_SCORE);
            this.isPlayerTurn = score.getRoundCounter() % 2 == 0;
        } else {
            this.isPlayerTurn = false;
        }
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

    public boolean isIsPlayerTurn() {
        return isPlayerTurn;
    }

    public void setIsPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }
}
