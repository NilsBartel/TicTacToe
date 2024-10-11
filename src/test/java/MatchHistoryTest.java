import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MatchHistoryTest {



    private MatchHistory createMatchHistoryForTest(PrintService printService) {
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.setPrintService(printService);
        Match match = new Match();

        match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
        match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
        match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
        match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

        match.setIsPlayerTurn(true);
        match.setStartTime(1727709534293L);
        match.setEndTime(1727709537509L);
        match.setDifficulty(DifficultyState.EASY);
        match.setStatus(MatchStatus.DRAW);

        matchHistory.addMatch(match);

        return matchHistory;
    }

    @Test
    void printMatchHistoryTest() {

        PrintService mockPrintService = mock(PrintService.class);
        MatchHistory matchHistory = createMatchHistoryForTest(mockPrintService);

        matchHistory.printMatchHistory();

        verify(mockPrintService).printBoardNr(anyInt());
        verify(mockPrintService).printBoard(any());
        verify(mockPrintService).printSecondsElapsed(any(), any());
        verify(mockPrintService).printDate(any());
    }


    private Match createMatchForTest() {
        Match match = new Match();
        match.setSymbol(0, 0, Match.PLAYER_SYMBOL);
        match.setSymbol(1, 1, Match.PLAYER_SYMBOL);
        match.setSymbol(0, 1, Match.COMPUTER_SYMBOL);
        match.setSymbol(2, 2, Match.COMPUTER_SYMBOL);

        match.setIsPlayerTurn(true);
        match.setStartTime(1727709534293L);
        match.setEndTime(1727709537509L);
        return match;
    }
    @Test
    void addMatchTest() {
        MatchHistory matchHistory = new MatchHistory();
        int testInt = matchHistory.getMatches().size();

        matchHistory.addMatch(createMatchForTest());

        assertEquals(matchHistory.getMatches().size(), testInt + 1);
    }
}