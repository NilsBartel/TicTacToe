public class Match {
    // game loop

    private final Board board;
    private MatchStatus status;
    private final char playerSymbol = 'o';
    private final char computerSymbol = 'x';
    private boolean isPlayerTurn = true;
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
//
//        board.print();

        if(isPlayerTurn){
            currentSymbol = playerSymbol;
        } else{
            currentSymbol = computerSymbol;
        }







        board.print();
        while (true){

            int move;

            if(isPlayerTurn){
                currentSymbol = playerSymbol;
                move = PlayerInput.askForMove(board);

            } else{
                currentSymbol = computerSymbol;
                move = ComputerMoveService.randomMove(board);
            }


            //int playerMove = PlayerMoveService.askForMove();
            //System.out.println(playerMove);
            Position playerPosition = new Position(move);
            int row = playerPosition.getRow();
            int column = playerPosition.getColumn();

            board.getRows().get(row).getFields().get(column).setSymbol(currentSymbol);



            board.print();

            if (Winner.thereIsWinner(board, move, currentSymbol)) {


                if (currentSymbol == computerSymbol){
                    this.status = MatchStatus.COMPUTER_WON;
                } else this.status = MatchStatus.PLAYER_WON;




                //Winner.printWinner(currentSymbol, computerSymbol, playerSymbol);
                break;
            }

            moveCounter++;
            if (moveCounter > 9-1) {
                //System.out.println("It's a draw!");
                this.status = MatchStatus.DRAW;
                break;
            }

            isPlayerTurn = !isPlayerTurn;

        }













        /*int n = 0;


        board.print();
        while (n<9){

            if(isPlayerTurn){
                int playerMove = PlayerMoveService.askForMove();
                //System.out.println(playerMove);
                Position playerPosition = new Position(playerMove);
                int row = playerPosition.getRow();
                int column = playerPosition.getColumn();

                board.getRows().get(row).getFields().get(column).setSymbol(playerSymbol);
                CheckWinner.thereIsWinner(board, playerMove, playerSymbol);
            } else {

                int computerMove = ComputerMoveService.randomMove(board);
                //System.out.println(computerMove);

                Position computerPosition = new Position(computerMove);

                int row = computerPosition.getRow();
                int column = computerPosition.getColumn();
                board.getRows().get(row).getFields().get(column).setSymbol(computerSymbol);
                CheckWinner.thereIsWinner(board, computerMove, computerSymbol);
            }







            board.print();

            isPlayerTurn = !isPlayerTurn;
            n++;
        }*/
















        //System.out.println(row);
        //System.out.println(column);















        /*printField();

        while (true) {

            int[] coordinaten;

            if (moveCounter % 2 == whichPlayerStarts) {
                int zug;

                do{
                    zug = spielerNachZugFragen(playerChar);
                } while (!isValid(zug));

                coordinaten = returnCoordinates(zug);
                spielFeld[coordinaten[0]][coordinaten[1]] = playerChar;


            } else {
                coordinaten = returnCoordinates(computerMove());
                spielFeld[coordinaten[0]][coordinaten[1]] = computerChar;
            }


            System.out.println();
            printField();

            if (moveCounter > 3) {
                System.out.println("testing");
                if (checkForWinner(coordinaten, true)) {
                    break;
                }
            }
            moveCounter++;

            if (moveCounter > fieldSize*fieldSize-1) {
                System.out.println("Es ist gleichstand!");
                break;
            }
        }*/
    }



    public Board getBoard() {
        return board;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public boolean getIsPlayerTurn() {
        return isPlayerTurn;
    }
}
