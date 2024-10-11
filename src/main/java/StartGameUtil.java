public class StartGameUtil {

    public static boolean loadAbandonedGame(MatchHistory matchHistory) {
        if (!matchHistory.getMatches().isEmpty() && (matchHistory.compareLastStatus(MatchStatus.RUNNING) || matchHistory.compareLastStatus(MatchStatus.NOT_STARTED))) {
            System.out.println("Welcome back, your last game has been restored.");
            System.out.println();
            return true;
        } else {
            System.out.println("Welcome to TicTacToe!");
            System.out.println();
            return false;
        }
    }

    public static Match returnRunningOrNewMatch(MatchHistory matchHistory, DifficultyState difficulty) {


        Match match;
        if (!matchHistory.getMatches().isEmpty() && (matchHistory.compareLastStatus(MatchStatus.RUNNING) || matchHistory.compareLastStatus(MatchStatus.NOT_STARTED) || matchHistory.compareLastStatus(MatchStatus.MATCH_ALREADY_FINISHED))) {
            match = matchHistory.getMatches().getLast();
        } else {
            match = new Match();
            match.setDifficulty(difficulty);
            matchHistory.addMatch(match);
            FileWriteRead.getInstance().writeToHistoryFile(Main.FILE_MATCH_HISTORY, matchHistory);
        }

        return match;
    }










}
