import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final ArrayList<Position> DIAGONAL_TOP_RIGHT_BOTTOM_LEFT = new ArrayList<>(List.of(new Position(3), new Position(5), new Position(7)));
    public static final ArrayList<Position> DIAGONAL_TOP_LEFT_BOTTOM_RIGHT = new ArrayList<>(List.of(new Position(1), new Position(5), new Position(9)));

    ArrayList<Row> rows = new ArrayList<>();


    public Board(){
        for(int row = 0; row<3; row++){

            rows.add(new Row(row));
        }
    }

    public void print(){
        for(Row row : rows){
            row.print();
        }


    }


    public boolean isValid(int index){


        if(index < 1 || index > 9) return false;


        Position position = new Position(index);
        int row = position.getRow();
        int column = position.getColumn();

        return (rows.get(row).getFields().get(column).getSymbol() == ' ');
    }




    public ArrayList<Row> getRows() {
        return rows;
    }
}
