public class Match {
    // game loop

    private final Board board;
    private MatchStatus status;


    public Match() {
        this.board = new Board();
        this.status = MatchStatus.NOT_STARTED;
    }


    public void play(){
        board.print();




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
}
