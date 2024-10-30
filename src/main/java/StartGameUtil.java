public final class StartGameUtil {
    private StartGameUtil() {
    }


    public static Match returnRunningOrNewMatch(MatchHistory matchHistory, DifficultyState difficulty, FileWriteRead fileWriteRead) {

        Match match;

        if (!matchHistory.getMatches().isEmpty() && (matchHistory.compareLastStatus(MatchStatus.RUNNING) || matchHistory.compareLastStatus(MatchStatus.NOT_STARTED) || matchHistory.compareLastStatus(MatchStatus.MATCH_ALREADY_FINISHED))) {
            System.out.println("loaded a match");
            match = matchHistory.getMatches().getLast();
        } else {
            System.out.println("Creating a new match");
            match = new Match();
            match.setDifficulty(difficulty);
            if (difficulty == null) {
                match.setDifficulty(PlayerInput.getInstance().askForDifficulty());
            }
            matchHistory.addMatch(match);
            fileWriteRead.writeToHistoryFile(FileUtil.getInstance().getFileMatchHistory(), matchHistory);
        }

        return match;
    }

}
