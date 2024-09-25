import java.util.ArrayList;
import java.util.List;


public class MatchHistory {
    private static final int MATCH_HISTORY_LENGTH = 10;


    private List<Integer> matchList = new ArrayList<>();







    public int convertMatchToInt(Board board) {
        int number = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                char symbol = board.getSymbol(row, column);

                if (symbol == Match.COMPUTER_SYMBOL) {
                    number = number * 10 + 1;
                } else if (symbol == Match.PLAYER_SYMBOL) {
                    number = number * 10 + 2;
                } else { // field is empty
                    number = number * 10 + 3;
                }
            }
        }
         return number;
    }









    public void addNewMatchToList(int match){

        for(int i = matchList.size()-1; i > 0; i--){
            if(matchList.get(i) != null){
                matchList.set(i, matchList.get(i-1));
            }
        }
        if(matchList.isEmpty()){
            matchList.add(match);
        } else {
            matchList.set(0, match);
        }


        if(matchList.size() == MATCH_HISTORY_LENGTH+1){
            matchList.remove(MATCH_HISTORY_LENGTH);
        }
    }







    public List<Integer> getMatchList() {
        return matchList;
    }









    public void printList(){
        System.out.println(matchList);
    }

    public void populateList(){
        for(int i = 0; i < 11; i++){
            matchList.add(i);
        }
    }
}
