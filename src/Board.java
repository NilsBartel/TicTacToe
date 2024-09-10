import java.util.ArrayList;

public class Board {
    // game board
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



}
