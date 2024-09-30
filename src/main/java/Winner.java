import java.util.ArrayList;
import java.util.List;

public final class Winner {
    private Winner() {
    }

    public static boolean thereIsWinner(Board board, Position position, char currentSymbol) {


        int row = position.getRow();
        if(allEqualsSymbol(board.getFieldsInRow(row), currentSymbol)) {
            return true;
        }


        List<Field> column = new ArrayList<>();
        for(Row line : board.getRows()) {
            column.add(line.getField(position.getColumn()));
        }
        if(allEqualsSymbol(column, currentSymbol)) {
            return true;
        }


        List<Field> diagonalLeftRight = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT){
            diagonalLeftRight.add(board.getField(pos.getRow(), pos.getColumn()));
        }
        if(allEqualsSymbol(diagonalLeftRight, currentSymbol)) {
            return true;
        }


        List<Field> diagonalRightLeft = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT){
            diagonalRightLeft.add(board.getField(pos.getRow(), pos.getColumn()));
        }
        return allEqualsSymbol(diagonalRightLeft, currentSymbol);
    }


    private static boolean allEqualsSymbol(List<Field> fields, char currentSymbol) {

        for(Field field : fields) {
            if (field.getSymbol() != currentSymbol) {
                return false;
            }
        }
        return true;
    }



}
