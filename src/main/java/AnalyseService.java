import java.util.*;

public class AnalyseService {



    //TODO: make Field.class have a Position?
    public static void findBestWinningLine(){


        Map<List<Position>, Integer> map = createMap();
        MatchHistory matchHistory = MatchHistory.fromFile();
        List<List<Position>> wins = new ArrayList<>();

        for (Match match : matchHistory.getMatches()) {
            // unless its a draw
            if (match.getStatus() == MatchStatus.COMPUTER_WON || match.getStatus() == MatchStatus.PLAYER_WON){
                wins.add(Winner.findWinningRow(match.getBoard(), getWinnerSymbol(match.getStatus())));
            }
        }

        for (List<Position> win : wins) {

            if (map.containsKey(win)) {
                map.put(win, map.get(win) + 1);
            }
        }



        List<Position> winPositions = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

        for (Position position : winPositions) {
            System.out.print(position.getIndex() + " ");
        }




//        Match match = matchHistory.getMatches().getLast();
//        if (match.getStatus() == MatchStatus.COMPUTER_WON || match.getStatus() == MatchStatus.PLAYER_WON){
//            wins.add(Winner.findWinningRow(match.getBoard(), getWinnerSymbol(match.getStatus())));
//
//            System.out.println(wins.getFirst().get(0).getIndex());
//            System.out.println(wins.getFirst().get(1).getIndex());
//            System.out.println(wins.getFirst().get(2).getIndex());
//            wins.clear();
//        }





    }

    private static char getWinnerSymbol(MatchStatus status) {
        switch (status) {
            case COMPUTER_WON -> {
                return Match.COMPUTER_SYMBOL;
            }
            case PLAYER_WON -> {
                return Match.PLAYER_SYMBOL;
            }
            default -> {
                return 'p';
            }

        }
    }

    private static Map<List<Position>, Integer> createMap() {
        Map<List<Position>, Integer> map = new HashMap<>();

        map.put(Arrays.asList(new Position(1),new Position(2),new Position(3)), 0);
        map.put(Arrays.asList(new Position(4),new Position(5),new Position(6)), 0);
        map.put(Arrays.asList(new Position(7),new Position(8),new Position(9)), 0);

        map.put(Arrays.asList(new Position(1),new Position(4),new Position(7)), 0);
        map.put(Arrays.asList(new Position(2),new Position(5),new Position(8)), 0);
        map.put(Arrays.asList(new Position(3),new Position(6),new Position(9)), 0);

        map.put(Arrays.asList(new Position(1),new Position(5),new Position(9)), 0);
        map.put(Arrays.asList(new Position(3),new Position(5),new Position(7)), 0);

        return map;
    }







}
