public class Field {

    private char symbol;
    private Position position;

    public Field(Position position) {
        this.position = position;
        this.symbol = 'x';
    }





    public char getSymbol() {
        return symbol;
    }

    public void setsymbol(char symbol, Position position) {
        this.symbol = symbol;
    }


}
