
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



    public static List<Position> findWinningRow(Board board, char winningSymbol) {

        
        List<Position> positions = new ArrayList<>();

        //Row
        List<Field> list = new ArrayList<>();
        for (int row = 0; row<3; row++) {
            for (int column = 0; column<3; column++) {
                list.add(board.getField(row, column));
                positions.add(new Position(row, column));
            }
            if(allEqualsSymbol(list, winningSymbol)) {
                return positions;
            } else {
                list.clear();
            }
            positions.clear();
        }


        //Column
        for (int column = 0; column<3; column++) {
            for (int row = 0; row<3; row++) {
                list.add(board.getField(row, column));
                positions.add(new Position(row, column));
            }
            if(allEqualsSymbol(list, winningSymbol)) {
                return positions;
            } else {
                list.clear();
            }
            positions.clear();
        }




        List<Field> diagonalLeftRight = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT){
            positions.add(pos);
            diagonalLeftRight.add(board.getField(pos.getRow(), pos.getColumn()));
        }
        if(allEqualsSymbol(diagonalLeftRight, winningSymbol)) {
            return positions;
        }
        positions.clear();



        List<Field> diagonalRightLeft = new ArrayList<>();
        for(Position pos : Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT){
            positions.add(pos);
            diagonalRightLeft.add(board.getField(pos.getRow(), pos.getColumn()));
        }
        if (allEqualsSymbol(diagonalRightLeft, winningSymbol)) {
            return positions;
        }

        return null;
    }

}
