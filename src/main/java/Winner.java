
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

    public static void test(Board board) {
        List<Field> rows = new ArrayList<>();

        for (Row row : board.getRows()) {
            rows.addAll(row.getFields());
            //rows.add(row.getField());
            System.out.println(rows);

        }
    }

    public static List<Position> findWinningRow(Board board, char winningSymbol) {

        //TODO:
        // guck ob das erste symbol der reihe mit dem "winningSymbol" übereinstimmen

        List<Field> rows = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        int counter = 1;

        //Row
        for (Row row : board.getRows()) {
            rows.addAll(row.getFields());
            if(allEqualsSymbol(rows, winningSymbol)) {
                positions.add(new Position(counter));
                positions.add(new Position(counter+1));
                positions.add(new Position(counter+2));

                return positions;
            } else {
                rows.clear();
            }
            counter += 3;
        }
        positions.clear();
        counter = 1;


        //Column
        List<Field> columns = new ArrayList<>();
        for (int column = 0; column<3; column++) {
            for (int row = 0; row<3; row++) {
                columns.add(board.getField(row, column));
                //TODO maybe one for-each?

            }
            if(allEqualsSymbol(columns, winningSymbol)) {
                positions.add(new Position(counter));
                positions.add(new Position(counter+3));
                positions.add(new Position(counter+6));

                return positions;
            } else {
                columns.clear();
            }
            counter++;
        }


        positions.clear();

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













        // do that for all the types (row, column, diagonal

        System.out.println("returned null");
        return null;
    }

//    private static List<Position> convertToPositions(List<Field> fields) {
//
//        List<Position> positions = new ArrayList<>();
//        for(Field field : fields) {
//            Row row;
//            row.g
//        }
//    }



}
