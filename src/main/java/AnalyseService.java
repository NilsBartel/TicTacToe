import java.util.*;

public final class AnalyseService {
    private static AnalyseService instance;
    private AnalyseService() {
    }

    public static AnalyseService getInstance() {
        if (instance == null) {
            instance = new AnalyseService();
        }
        return instance;
    }


    public Map<List<Position>, Integer> findBestWinningLine(MatchHistory matchHistory) {

        //MatchHistory matchHistory = MatchHistory.fromFile(file);

        Map<List<Position>, Integer> map = new HashMap<>();
        List<List<Position>> wins = new ArrayList<>();

        for (Match match : matchHistory.getMatches()) {
            if (match.getStatus() == MatchStatus.COMPUTER_WON || match.getStatus() == MatchStatus.PLAYER_WON){
                wins.add(Winner.findWinningRow(match.getBoard(), getWinnerSymbol(match.getStatus())));
            }
        }

        for (List<Position> win : wins) {

            if (map.containsKey(win)) {
                map.put(win, map.get(win) + 1);
            } else {
                map.put(win, 1);
            }
        }

//        for (int i = 0; i < map.size(); i++) {
//            List<Position> winPositions = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
//            int count =  Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
//            map.remove(winPositions);
//
//            for (Position position : winPositions) {
//                System.out.print(position.getIndex() + " ");
//            }
//            System.out.println("- Happened "+count+" times.");
        //}
//        List<Position> winPositions = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
//        int count =  Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
//        map.remove(winPositions);
//
//        for (Position position : winPositions) {
//            System.out.print(position.getIndex() + " ");
//        }
//        System.out.println("- Happened "+count+" times.");

        return map;

    }

    private Character getWinnerSymbol(MatchStatus status) {
        switch (status) {
            case COMPUTER_WON -> {
                return Match.COMPUTER_SYMBOL;
            }
            case PLAYER_WON -> {
                return Match.PLAYER_SYMBOL;
            }
            default -> {
                return null;
            }
        }
    }

}
