import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MatchHistoryTest {



    private MatchHistory createMatchHistory(PrintService printService) {
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

        matchHistory.addMatch(match);

        return matchHistory;
    }

    @Test
    void printMatchHistoryTest() {

        PrintService mockPrintService = mock(PrintService.class);
        MatchHistory matchHistory = createMatchHistory(mockPrintService);

        matchHistory.printMatchHistory();

        verify(mockPrintService).printBoardNr(anyInt());
    }
}