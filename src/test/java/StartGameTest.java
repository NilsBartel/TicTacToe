import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StartGameTest {

    // check: start game with empty MatchHistory
    // check: start game with finished game in MatchHistory
    // check: start game with a started match (running match in matchHistory)




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
        PrintService mockPrintService = mock(PrintService.class);
        PlayerInput mockPlayerInput = mock(PlayerInput.class);
        FileWriteRead fileWriteRead = mock(FileWriteRead.class);
        MatchHistory matchHistory = createMatchHistoryFor_StartWithRunningMatchTest();

        when(fileWriteRead.readFromHistoryFile(any())).thenReturn(matchHistory);
        when(mockPlayerInput.askForDifficulty()).thenReturn(DifficultyState.EASY);
        when(mockPlayerInput.askPlayAgainWithHistory()).thenReturn(false);


        StartGame startGame = new StartGame();
        startGame.setPrintService(mockPrintService);
        startGame.setPlayerInput(mockPlayerInput);
        startGame.setFileWriteRead(fileWriteRead);
        startGame.start();

        verify(mockPrintService).printTest1();
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
        PrintService mockPrintService = mock(PrintService.class);
        PlayerInput mockPlayerInput = mock(PlayerInput.class);
        FileWriteRead fileWriteRead = mock(FileWriteRead.class);
        MatchHistory matchHistory = createMatchHistoryFor_startWithEndedMatchTest();

        when(fileWriteRead.readFromHistoryFile(any())).thenReturn(matchHistory);
        when(mockPlayerInput.askForDifficulty()).thenReturn(DifficultyState.EASY);
        when(mockPlayerInput.askPlayAgainWithHistory()).thenReturn(false);


        StartGame startGame = new StartGame();
        startGame.setPrintService(mockPrintService);
        startGame.setPlayerInput(mockPlayerInput);
        startGame.setFileWriteRead(fileWriteRead);
        startGame.start();

        verify(mockPrintService).printTest2();
    }


    @Test
    void startWithEmptyMatchHistoryTest() {
        PrintService mockPrintService = mock(PrintService.class);
        PlayerInput mockPlayerInput = mock(PlayerInput.class);
        FileWriteRead fileWriteRead = mock(FileWriteRead.class);
        //Match mockMatch = mock(Match.class);
        MatchHistory matchHistory = new MatchHistory();

        when(fileWriteRead.readFromHistoryFile(any())).thenReturn(matchHistory);
        when(mockPlayerInput.askForDifficulty()).thenReturn(DifficultyState.EASY);
        when(mockPlayerInput.askPlayAgainWithHistory()).thenReturn(false);
        //Mockito.doNothing().when(mockMatch).play(any());


        StartGame startGame = new StartGame();
        startGame.setPrintService(mockPrintService);
        startGame.setPlayerInput(mockPlayerInput);
        startGame.setFileWriteRead(fileWriteRead);
        startGame.start();

        verify(mockPrintService).printTest2();
    }



}