public class Field {

    private char symbol;
    private Position position;

    public Field(Position position) {
        this.position = position;
        this.symbol = ' ';
    }





    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


}
