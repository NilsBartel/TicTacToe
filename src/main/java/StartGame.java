public class StartGame {


    public void start() {
        PrintService printService = PrintService.getInstance();
        PlayerInput playerInput = PlayerInput.getInstance();
        FileWriteRead fileWriteRead = FileWriteRead.getInstance();



        Users users = fileWriteRead.readFromUserFile(FileService.getInstance().getFileUserData());

        if (!LogIn.logInUser(users)) {
            System.out.println();
            System.out.println("Login failed");
            start();
        }
        System.out.println("Successfully logged in!");





//        String userName = playerInput.askForUserName();
//
//        if (LogIn.userNameExists(userName, users)) {
//            if (LogIn.validateUser(userName, users)) {
//
//            }
//        } else {
//            userName = LogIn.createUser(users);
//        }
//
//
//        userName = LogIn.validateUser(userName, users);
//        if (userName == null) {
//            System.out.println("Returned to start!");
//            this.start();
//        }
//        System.out.println("Successfully logged in!");
//        FileService.getInstance().setFileName(userName);




        DifficultyState difficulty = null;
        do {

            MatchHistory matchHistory = fileWriteRead.readFromHistoryFile(FileService.getInstance().getFileMatchHistory());
            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficulty, fileWriteRead);
            difficulty = match.getDifficulty();

            match.play(matchHistory);

            Winner.printWhoWon(match.getStatus());
            matchHistory.updateScore(match.getStatus());


            displayScore(matchHistory.getScore());
            fileWriteRead.writeToHistoryFile(FileService.getInstance().getFileMatchHistory(), matchHistory);


        } while (playerInput.askPlayAgain());

        printService.printGameEndMessage();
    }


    private void displayScore(Score score) {
        PrintService.getInstance().printScore(score);
        PrintService.getInstance().printDrawCounter(score);
        PrintService.getInstance().printRoundCounter(score);
    }

}