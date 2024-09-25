import java.util.ArrayList;
import java.util.List;


public class MatchHistory {
    private static final int MATCH_HISTORY_LENGTH = 10;


    private final List<Integer> matchList = new ArrayList<>();

    public int convertMatchToInt(Board board) {
        int number = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                char symbol = board.getSymbol(row, column);

                if (symbol == Match.PLAYER_SYMBOL) {
                    number = number * 10 + 1;
                } else if (symbol == Match.COMPUTER_SYMBOL) {
                    number = number * 10 + 2;
                } else { // field is empty
                    number = number * 10 + 3;
                }
            }
        }
         return number;
    }

    public Board convertIntToBoard(int number) {
        Board board = new Board();

        List<Integer> numbers = new ArrayList<>();

        while(number > 0){
            numbers.add(number % 10);
            number = number / 10;
        }

        int counter = numbers.size() - 1;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if(numbers.get(counter) == 1) {
                    board.setSymbol(row, column, Match.PLAYER_SYMBOL);
                } else if (numbers.get(counter) == 2) {
                    board.setSymbol(row, column, Match.COMPUTER_SYMBOL);
                } else {
                    board.setSymbol(row, column, Match.EMPTY_SYMBOL);
                }
                counter--;
            }
        }
        return board;
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





    public void printMatchHistory(){

        MatchHistory matchHistory = JsonFileWriteRead.readHistoryFile(Main.FILE_MATCH_HISTORY);
        List<Integer> list = matchHistory.getMatchList();
        for(int i = 0; i < list.size(); i++){
            Board boardTest = matchHistory.convertIntToBoard(list.get(i));
            System.out.println("Board: " + (i+1));
            boardTest.print();
            System.out.println();
        }
    }

    public List<Integer> getMatchList() {
        return matchList;
    }


}
