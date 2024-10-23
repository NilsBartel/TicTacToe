import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class StartGameUtilTest {




        private MatchHistory createMatchHistoryFor_StartWithRunningMatchTest() {
            MatchHistory matchHistory = new MatchHistory();
            Match match = new Match();

            match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
            match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
            match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
            match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

            match.setIsPlayerTurn(true);
            match.setStartTime(1727709534293L);
            match.setEndTime(0);
            match.setDifficulty(DifficultyState.EASY);
            match.setStatus(MatchStatus.RUNNING);

            matchHistory.addMatch(match);

            return matchHistory;
        }
        @Test
        void startWithRunningMatchTest() {

            MatchHistory matchHistory = createMatchHistoryFor_StartWithRunningMatchTest();
            DifficultyState difficultyState = DifficultyState.EASY;
            FileWriteRead mockFileWriteRead = mock(FileWriteRead.class);

            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficultyState, mockFileWriteRead);

            assertEquals(match, matchHistory.getMatches().getLast());
        }


        private MatchHistory createMatchHistoryFor_startWithEndedMatchTest() {
            MatchHistory matchHistory = new MatchHistory();
            Match match = new Match();

            match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
            match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
            match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
            match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

            match.setIsPlayerTurn(true);
            match.setStartTime(1727709534293L);
            match.setEndTime(0);
            match.setDifficulty(DifficultyState.EASY);
            match.setStatus(MatchStatus.DRAW);

            matchHistory.addMatch(match);

            return matchHistory;
        }
        @Test
        void startWithEndedMatchTest() {

            MatchHistory matchHistory = createMatchHistoryFor_startWithEndedMatchTest();
            DifficultyState difficultyState = DifficultyState.EASY;
            FileWriteRead mockFileWriteRead = mock(FileWriteRead.class);

            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficultyState, mockFileWriteRead);

            assertEquals(match, matchHistory.getMatches().getLast());
        }


        @Test
        void startWithEmptyMatchHistoryTest() {

            MatchHistory matchHistory = new MatchHistory();
            DifficultyState difficultyState = DifficultyState.EASY;
            FileWriteRead mockFileWriteRead = mock(FileWriteRead.class);

            Match match = StartGameUtil.returnRunningOrNewMatch(matchHistory, difficultyState, mockFileWriteRead);

            assertEquals(match, matchHistory.getMatches().getLast());
        }



        private Match generateMatchForTest_Draw() {
            Match match = new Match();

            match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
            match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
            match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
            match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

            match.setIsPlayerTurn(true);
            match.setStartTime(1727709534293L);
            match.setEndTime(0);
            match.setDifficulty(DifficultyState.EASY);
            match.setStatus(MatchStatus.DRAW);
            return match;
        }
        @Test
        void updateScoreTest_Draw() {
            PrintService mockPrintService = mock(PrintService.class);
            MatchHistory mockHistory = mock(MatchHistory.class);
            Match match = generateMatchForTest_Draw();
            Score score = new Score();
            when(mockHistory.getScore()).thenReturn(score);
            doNothing().when(mockHistory).updateScore(any());

            score = StartGameUtil.updateScore(match, mockPrintService, mockHistory);

            assertEquals(1, score.getDrawCount());
        }

    private Match generateMatchForTest_PlayerWin() {
        Match match = new Match();

        match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
        match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
        match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
        match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

        match.setIsPlayerTurn(true);
        match.setStartTime(1727709534293L);
        match.setEndTime(0);
        match.setDifficulty(DifficultyState.EASY);
        match.setStatus(MatchStatus.PLAYER_WON);
        return match;
    }
    @Test
    void updateScoreTest_PlayerWin() {
        PrintService mockPrintService = mock(PrintService.class);
        MatchHistory mockHistory = mock(MatchHistory.class);
        Match match = generateMatchForTest_PlayerWin();
        Score score = new Score();
        when(mockHistory.getScore()).thenReturn(score);
        doNothing().when(mockHistory).updateScore(any());

        score = StartGameUtil.updateScore(match, mockPrintService, mockHistory);

        assertEquals(1, score.getPlayerScore());
    }

    private Match generateMatchForTest_ComputerWin() {
        Match match = new Match();

        match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
        match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
        match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
        match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

        match.setIsPlayerTurn(true);
        match.setStartTime(1727709534293L);
        match.setEndTime(0);
        match.setDifficulty(DifficultyState.EASY);
        match.setStatus(MatchStatus.COMPUTER_WON);
        return match;
    }
    @Test
    void updateScoreTest_ComputerWin() {
        PrintService mockPrintService = mock(PrintService.class);
        MatchHistory mockHistory = mock(MatchHistory.class);
        Match match = generateMatchForTest_ComputerWin();
        Score score = new Score();
        when(mockHistory.getScore()).thenReturn(score);
        doNothing().when(mockHistory).updateScore(any());

        score = StartGameUtil.updateScore(match, mockPrintService, mockHistory);

        assertEquals(1, score.getComputerScore());
    }



}