import java.util.ArrayList;
import java.util.List;


public class MatchHistory {
    private static final int MATCH_HISTORY_LENGTH = 10;
    private static final int PLAYER_INT = 1;
    private static final int COMPUTER_INT = 2;
    private static final int EMPTY_FILED_INT = 3;


    private final List<Integer> matchList = new ArrayList<>();


    public void addBoardToHistory(Board board) {
        int number = convertBoardToInt(board);
        addIntToList(number);
    }

    private int convertBoardToInt(Board board) {
        int number = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                char symbol = board.getSymbol(row, column);

                if (symbol == Match.PLAYER_SYMBOL) {
                    number = number * 10 + PLAYER_INT;
                } else if (symbol == Match.COMPUTER_SYMBOL) {
                    number = number * 10 + COMPUTER_INT;
                } else { // field is empty
                    number = number * 10 + EMPTY_FILED_INT;
                }
            }
        }
         return number;
    }

    private Board convertIntToBoard(int number) {
        int newNumber = number;
        Board board = new Board();
        List<Integer> numbers = new ArrayList<>();

        while(newNumber > 0){
            numbers.add(newNumber % 10);
            newNumber = newNumber / 10;
        }

        numbers = numbers.reversed();

        int counter = 1;
        for(int num : numbers ){
            Position position = new Position(counter);
            switch (num){
                case PLAYER_INT:
                    board.setSymbol(position.getRow(), position.getColumn(), Match.PLAYER_SYMBOL);
                    break;
                case COMPUTER_INT:
                    board.setSymbol(position.getRow(), position.getColumn(), Match.COMPUTER_SYMBOL);
                    break;
                case EMPTY_FILED_INT:
                    board.setSymbol(position.getRow(), position.getColumn(), Match.EMPTY_SYMBOL);
                    break;

            }
            counter++;
        }
        return board;
    }




    private void addIntToList(int match){

        matchList.addFirst(match);

        if(matchList.size() > MATCH_HISTORY_LENGTH){
            matchList.remove(MATCH_HISTORY_LENGTH);
        }

    }

    public void printMatchHistory(){

        MatchHistory matchHistory = JsonFileWriteRead.readHistoryFile(Main.FILE_MATCH_HISTORY);
        List<Integer> list = matchHistory.getMatchList();
        //List<Integer> list = matchList;
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
