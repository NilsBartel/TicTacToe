public class Match {

    private final Board board;
    private MatchStatus status;
    //private boolean isPlayerTurn;
    public static final char PLAYER_SYMBOL = 'o';
    public static final char COMPUTER_SYMBOL = 'x';
    public static final char EMPTY_SYMBOL = ' ';



    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
    }


    public void play(int mediumDifficulty) {

        this.status = MatchStatus.RUNNING;

        int moveCounter = 0;
        char currentSymbol;

        if (Main.FILE_SCORE.exists() && Main.FILE_SCORE.length() != 0) {
            Score score = JsonFileWriteRead.readFile(Main.FILE_SCORE);
            Main.isPlayerTurn = ((score.getRoundCounter() % 2 == 0));
        } else {
            Main.isPlayerTurn = false;
        }


        board.print();
        System.out.println();
        while (true){

            Position position;
            if(Main.isPlayerTurn){
                currentSymbol = PLAYER_SYMBOL;
                position = PlayerInput.askForMove(board);
            } else{
                currentSymbol = COMPUTER_SYMBOL;
                position = Difficulty.returnMove(board, mediumDifficulty);
            }

            board.setSymbol(position.getRow(), position.getColumn(), currentSymbol);

            System.out.println();
            board.print();


            if (Winner.thereIsWinner(board, position, currentSymbol)) {
                if (currentSymbol == COMPUTER_SYMBOL){
                    this.status = MatchStatus.COMPUTER_WON;
                } else{
                    this.status = MatchStatus.PLAYER_WON;
                }
                break;
            }

            moveCounter++;
            if (moveCounter > 9-1) {
                this.status = MatchStatus.DRAW;
                break;
            }

            Main.isPlayerTurn = !Main.isPlayerTurn;

        }

        if (Main.FILE_MATCH_HISTORY.exists() && Main.FILE_MATCH_HISTORY.length() != 0) {
            MatchHistory matchHistory = JsonFileWriteRead.readHistoryFile(Main.FILE_MATCH_HISTORY);
            matchHistory.addBoardToHistory(board);
            JsonFileWriteRead.writeHistoryFile(Main.FILE_MATCH_HISTORY, matchHistory);
        } else {
            MatchHistory matchHistory = new MatchHistory();
            matchHistory.addBoardToHistory(board);
            JsonFileWriteRead.writeHistoryFile(Main.FILE_MATCH_HISTORY, matchHistory);
        }


        //TODO: make the play() function more readable by building more functions


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
}
