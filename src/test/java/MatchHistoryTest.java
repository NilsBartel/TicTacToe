import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MatchHistoryTest {


    //TODO: create object Match? or matchHistory? to feed to the when().then()

    //TODO: generate board method from other tests
    private static Board generateBoard(int[] player, int[] computer) {
        Board board = new Board();

        for (int j : player) {
            Position position = new Position(j);
            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.PLAYER_SYMBOL);
        }

        for (int j : computer) {
            Position position = new Position(j);
            board.getRows().get(position.getRow()).getFields().get(position.getColumn()).setSymbol(Match.COMPUTER_SYMBOL);
        }

        return board;
    }

    private MatchHistory createMatchHistory() {
        MatchHistory matchHistory = new MatchHistory();
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


//    @Test
//    void printTest() {
//
//        MatchHistory matchHistory = createMatchHistory();
//        matchHistory.printMatch();
//        //PrintService mockPrintService = mock(PrintService.class);
//
//
//        //when(mockPrintService.printBoardNr(anyInt())).thenReturn(any());
//
//-
//
//        //verify(PrintService.getInstance()).printBoardNr(anyInt());
//    }

    @Test
    void test() {
        List<String> mockedList = mock(List.class);
        mockedList.size();

        verify(mockedList).size();
    }



    @Test
    void printMatchHistoryTest() {
        
        //FileWriteRead fileWriteRead = mock(FileWriteRead.class);
        MatchHistory matchHistory = createMatchHistory();
        Match match = mock(Match.class);
        PrintService mockPrintService = mock(PrintService.class);

        //when(fileWriteRead.readFromHistoryFile(new File(anyString()))).thenReturn(matchHistory);
        //when(mockPrintService.printBoardNr(anyInt()).thenReturn(any());
        matchHistory.printMatchHistory(matchHistory);


        //verify(mockPrintService).printBoardNr(anyInt());
        //verify(match).printBoard();
        verify(mockPrintService).printBoardNr(anyInt());
        //verify(matchHistory).printMatchHistory(any());

    }
}