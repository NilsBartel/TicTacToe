
import java.util.Objects;

public class Match {

    private Board board; //final
    private MatchStatus status;
    private DifficultyState difficulty;
    public static final char PLAYER_SYMBOL = 'o';
    public static final char COMPUTER_SYMBOL = 'x';
    public static final char EMPTY_SYMBOL = ' ';
    private boolean isPlayerTurn;
    private long startTime;
    private long endTime;



    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
        startTime = System.currentTimeMillis();
    }


    public void play(MatchHistory matchHistory) {

        if (status == MatchStatus.NOT_STARTED) {
            setPlayerTurn(matchHistory.getScore());
        }

        this.status = MatchStatus.RUNNING;

        board.print();
        System.out.println();


        while (true){

            char currentSymbol;

            Position position;
            if(isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;

                position = playerMove(board);
                //position = ComputerMoveService.randomMove(board);
                if(position == null){
                    return;
                }

            } else{
                currentSymbol = COMPUTER_SYMBOL;
                position = Difficulty.returnMove(board, difficulty);
            }

            board.setSymbol(position.getRow(), position.getColumn(), currentSymbol);

            System.out.println();
            board.print();

            if(isGameOver(board, position, currentSymbol)){
                break;
            }

            isPlayerTurn = !isPlayerTurn;
            writeToHistoryFile(matchHistory);
        }


        endTime = System.currentTimeMillis();
        writeToHistoryFile(matchHistory);
    }

    private Position playerMove(Board board) {

        Match tempMatch = FileWriteRead.getInstance().readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast();
        Position position = PlayerInput.getInstance().askForMove(board);

        if (!tempMatch.equals(FileWriteRead.getInstance().readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast())) {
            do {
                if (!FileWriteRead.getInstance().compareToLastMatchState(MatchStatus.RUNNING)){
                    System.out.println();
                    System.out.println("The game you were playing has already been finished! too slow");
                    this.status = MatchStatus.MATCH_ALREADY_FINISHED;
                    return null;
                }

                if (!FileWriteRead.getInstance().compareToLastMatchStartTime(this.startTime)) {
                    System.out.println();
                    System.out.println("The game you were playing has already been finished! And a new one has started");
                    this.status = MatchStatus.MATCH_ALREADY_FINISHED;
                    return null;
                }

                System.out.println();
                System.out.println("The Board has changed!");
                System.out.println("New Board:");
                this.board = FileWriteRead.getInstance().getLastBoard();
                this.board.print();

                tempMatch = FileWriteRead.getInstance().readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast();
                position = PlayerInput.getInstance().askForMove(board);
            } while (!tempMatch.equals(FileWriteRead.getInstance().readFromHistoryFile(FileService.getInstance().getFileMatchHistory()).getMatches().getLast()));
        }

        return position;
    }

    private boolean isGameOver(Board board, Position position, char currentSymbol) {

        if (Winner.thereIsWinner(board, position, currentSymbol)) {
            if (currentSymbol == COMPUTER_SYMBOL){
                this.status = MatchStatus.COMPUTER_WON;
            } else{
                this.status = MatchStatus.PLAYER_WON;
            }
            return true;
        }

        if (board.isFull()) {
            this.status = MatchStatus.DRAW;
            return true;
        }
        return false;
    }

    private void writeToHistoryFile(MatchHistory history) {
        FileWriteRead.getInstance().writeToHistoryFile(FileService.getInstance().getFileMatchHistory(), history);
    }


    private void setPlayerTurn(Score score) {
        this.isPlayerTurn = score.getRoundCounter() % 2 == 0;
    }


    public void printBoard() {
        board.print();
    }

    public MatchStatus getStatus() {
        return status;
    }

    public boolean isStatusEqual(MatchStatus newStatus) {
        return this.status == newStatus;
    }

    public static char getOpponentsSymbol(char symbol) {
        if (symbol == COMPUTER_SYMBOL){
            return PLAYER_SYMBOL;
        } else if (symbol == PLAYER_SYMBOL){
            return COMPUTER_SYMBOL;
        }
        return symbol;
    }

    public Board getBoard() {
        return board;
    }

    public void setSymbol(int row, int column, char symbol) {
        board.setSymbol(row, column, symbol);
    }


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

    public DifficultyState getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyState difficulty) {
        this.difficulty = difficulty;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Match match)) {
            return false;
        }
        return isPlayerTurn == match.isPlayerTurn && startTime == match.startTime && endTime == match.endTime && board.equals(match.board) && status == match.status && difficulty == match.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, status, difficulty, isPlayerTurn, startTime, endTime);
    }


}


