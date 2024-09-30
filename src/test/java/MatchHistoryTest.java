import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatchHistoryTest {


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




    private static Stream<Arguments> generateBoardsForAddBoardToListTest() {
        return Stream.of(
                // tests all player chars
                Arguments.of(generateBoard(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{}), 111111111),

                // tests all computer chars
                Arguments.of(generateBoard(new int[]{}, new int[]{1,2,3,4,5,6,7,8,9}), 222222222),

                // tests all empty chars
                Arguments.of(generateBoard(new int[]{}, new int[]{}), 333333333),

                // tests another board
                Arguments.of(generateBoard(new int[]{1,4}, new int[]{2,5,8}), 123123323),
                Arguments.of(generateBoard(new int[]{1,2,3}, new int[]{7,8,9}), 111333222)


        );
    }
    @ParameterizedTest
    @MethodSource("generateBoardsForAddBoardToListTest")
    void addBoardToListTest(Board board, int expected) {
        MatchHistory matchHistory = new MatchHistory();
        matchHistory.addBoardToHistory(board);

        assertEquals(matchHistory.getMatchList().getFirst(), expected);
    }





















}