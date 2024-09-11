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




        // checks the diagonal top left to bottom right
        // 0/0, 1/1, 2/2
//        if(Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT.contains(position)) {
//
//            counter = 0;
//            for(Position diagonalPosition : Board.DIAGONAL_TOP_LEFT_BOTTOM_RIGHT) {
//                if (currentSymbol == board.getRows().get(diagonalPosition.getRow()).getFields().get(diagonalPosition.getColumn()).getSymbol()) {
//                    counter++;
//                } else break;
//                if (counter == board.rows.size()) {
//                    return true;
//                }
//            }
//        }






        // checks the diagonal top right to bottom left
        // 0/2, 1/1, 2/0     all = 2
//        if(Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT.contains(position)) {
//
//            counter = 0;
//            for(Position diagonalPosition : Board.DIAGONAL_TOP_RIGHT_BOTTOM_LEFT) {
//                if (currentSymbol == board.getRows().get(diagonalPosition.getRow()).getFields().get(diagonalPosition.getColumn()).getSymbol()) {
//                    counter++;
//                } else break;
//                if (counter == board.rows.size()) {
//                    return true;
//                }
//            }
//        }


        return false;
    }


    private static boolean allEqualsSymbol(ArrayList<Field> fields, char currentSymbol) {

        for(Field field : fields) {
            if (field.getSymbol() != currentSymbol) return false;

        }
        return true;
    }











}
