public class Position {

    private int index;
    private int row;
    private int column;


    public Position(int index){
        this.index = index;
        this.row = (index -1) / 3;
        this.column = (index -1) % 3 ;
    }

    public Position(int row, int column){
        this.index = row *3 + column +1;
        this.row = row;
        this.column = column;
    }





    public int getIndex() {
        return index;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}
