import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnalyseServiceTest {




    private Map<List<Position>, Integer> createMapForTest() {
        Map<List<Position>, Integer> map = new HashMap<>();
        map.put(List.of(new Position(1),new Position(5),new Position(9)),4);
        map.put(List.of(new Position(1),new Position(2),new Position(3)),4);
        map.put(List.of(new Position(3),new Position(6),new Position(9)),2);
        map.put(List.of(new Position(1),new Position(4),new Position(7)),2);
        map.put(List.of(new Position(4),new Position(5),new Position(6)),3);
        map.put(List.of(new Position(3),new Position(5),new Position(7)),1);
        return map;
    }
    @Test
    void analyseTest() {
        File file = new File("/Users/nilsbartel/IdeaProjects/TicTacToe/src/test/java/testMatchHistory.json");
        AnalyseService analyseService = AnalyseService.getInstance();
        MatchHistory matchHistory = MatchHistory.fromFile(file);

        Map<List<Position>, Integer> map =analyseService.findBestWinningLine(matchHistory);


        assertEquals(map, createMapForTest());
    }


    private Map<List<Position>, Integer> createMapForTest2() {
        Map<List<Position>, Integer> map = new HashMap<>();
        map.put(List.of(new Position(1),new Position(2),new Position(3)),1);
        map.put(List.of(new Position(4),new Position(5),new Position(6)),1);
        map.put(List.of(new Position(7),new Position(8),new Position(9)),1);

        map.put(List.of(new Position(1),new Position(4),new Position(7)),1);
        map.put(List.of(new Position(2),new Position(5),new Position(8)),1);
        map.put(List.of(new Position(3),new Position(6),new Position(9)),1);

        map.put(List.of(new Position(1),new Position(5),new Position(9)),1);
        map.put(List.of(new Position(3),new Position(5),new Position(7)),1);
        return map;
    }
    @Test
    void analyseTest2() {
        File file = new File("/Users/nilsbartel/IdeaProjects/TicTacToe/src/test/java/testMatchHistory2.json");
        AnalyseService analyseService = AnalyseService.getInstance();
        MatchHistory matchHistory = MatchHistory.fromFile(file);

        Map<List<Position>, Integer> map =analyseService.findBestWinningLine(matchHistory);

        assertEquals(map, createMapForTest2());
    }










}