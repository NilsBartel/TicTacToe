import java.util.ArrayList;

public class Winner {



    public static boolean thereIsWinner(Board board, Position position, char currentSymbol) {



        int row = position.getRow();

        //int column = position.getColumn();

        if(allEqualsSymbol(board.getRows().get(row).getFields(), currentSymbol)) return true;


        ArrayList<Field> column = new ArrayList<>();
        for(Row line : board.getRows()) {
            column.add(line.getFields().get(position.getColumn()));
        }
        if(allEqualsSymbol(column, currentSymbol)) return true;


        ArrayList<Field> diagonalLeftRight = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT){
            diagonalLeftRight.add(board.getRows().get(pos.getRow()).getFields().get(pos.getColumn()));
        }
        if(allEqualsSymbol(diagonalLeftRight, currentSymbol)) return true;


        ArrayList<Field> diagonalRightLeft = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT){
            diagonalRightLeft.add(board.getRows().get(pos.getRow()).getFields().get(pos.getColumn()));
        }
        if(allEqualsSymbol(diagonalRightLeft, currentSymbol)) return true;



        return false;
    }


    private static boolean allEqualsSymbol(ArrayList<Field> fields, char currentSymbol) {

        for(Field field : fields) {
            if (field.getSymbol() != currentSymbol) return false;

        }
        return true;
    }











}
