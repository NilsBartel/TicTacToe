import java.util.Random;

public class Main {

    static int computerScore = 0;
    static int playerScore = 0;
    static int drawCounter = 0;



    public static void main(String[] args) {


        
        int roundCounter = 0;
        System.out.println("Willkommen zu TicTacToe!");

        for(int i = 0; i < 100000; i++){
            //do {
            Match match = new Match();
            match.setPlayerTurn(roundCounter % 2 == 0);

            match.play();
            MatchStatus status = match.getStatus();



            switch (status) {
                case PLAYER_WON ->{
                    playerScore++;
                    //Output.printWhoWon(match.getIsPlayerTurn());
                }
                case COMPUTER_WON -> {
                    computerScore++;
                    //Output.printWhoWon(match.getIsPlayerTurn());
                }
                case DRAW -> {
                    drawCounter++;
                    //Output.printDraw();
                }
            }

            //Output.printScore(playerScore, computerScore);
            roundCounter++;
            //Output.printRoundCounter(roundCounter);
            //} while(PlayerInput.askPlayAgain());
        }




        Output.printScore(playerScore, computerScore);
        Output.printRoundCounter(roundCounter);
        Output.printDrawCounter(drawCounter);



        Output.printGameEndMessage();
    }


}