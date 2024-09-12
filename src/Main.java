import java.util.Random;

public class Main {

    static int computerScore = 0;
    static int playerScore = 0;

    //TODO: -> ComputerMoveService   when player starts dont just prevent one fork, there may be multiple (special move)       WIKIPEDIA: keiner gewinnt, da beide fehlerfrei spielen

    //TODO: look a bit more at the options on wikipedia

    // if opponent can create a fork, place two in a row to make him defend (unless that results in him winning or creating a fork)


    public static void main(String[] args) {



        int matchCount = 0;
        System.out.println("Willkommen zu TicTacToe!");

        do {
            Match match = new Match();
            match.setPlayerTurn(matchCount % 2 == 0);

            match.play();
            MatchStatus status = match.getStatus();

            switch (status) {
                case PLAYER_WON ->{
                    playerScore++;
                    Output.printWhoWon(match.getIsPlayerTurn());
                }
                case COMPUTER_WON -> {
                    computerScore++;
                    Output.printWhoWon(match.getIsPlayerTurn());
                }
                case DRAW -> Output.printDraw();
            }

            Output.printScore(playerScore, computerScore);
            matchCount++;
        } while(PlayerInput.askPlayAgain());


        Output.printGameEndMessage();
    }


}