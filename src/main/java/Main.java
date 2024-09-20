public final class Main {

    private static int computerScore = 0;
    private static int playerScore = 0;
    private static DifficultyState difficulty;

    private Main() {
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public static void main(String[] args) {

        int input = 0;
        if (args.length > 0) {
            try {
                input = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 100");
                System.exit(1);
            }
            if (input < 1 || input > 100) {
                System.out.println("Please enter a number between 1 and 100");
                System.exit(1);
            }
        } else {
            System.out.println("Please enter a number between 1 and 100");
            System.exit(1);
        }

        int mediumDifficultyPercentage = input;

        int roundCounter = 0;
        System.out.println("Welcome to TicTacToe!");
        System.out.println();


        PlayerInput.askForDifficulty();

        //for(int i = 0; i < 1000000; i++){
        do {
            Match match = new Match();
            match.setPlayerTurn(roundCounter % 2 == 0);

            match.play(mediumDifficultyPercentage);
            MatchStatus status = match.getStatus();


            switch (status) {
                case PLAYER_WON -> {
                    playerScore++;
                    Output.printWhoWon(match.getPlayerTurn());
                }
                case COMPUTER_WON -> {
                    computerScore++;
                    Output.printWhoWon(match.getPlayerTurn());
                }
                case DRAW -> {
                    Output.printDraw();
                }
                default -> {
                    System.out.println("Invalid match status");
                }
            }

            Output.printScore(playerScore, computerScore);
            roundCounter++;
            Output.printRoundCounter(roundCounter);
        } while (PlayerInput.askPlayAgain());

        //}


        //Output.printScore(playerScore, computerScore);
        //Output.printRoundCounter(roundCounter);
        //Output.printDrawCounter(drawCounter);


        Output.printGameEndMessage();
    }

    public static DifficultyState getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(DifficultyState difficulty) {
        Main.difficulty = difficulty;
    }

}