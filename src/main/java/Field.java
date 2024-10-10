import java.util.Objects;

public class Field {

    private char symbol;


    public Field() {
        this.symbol = ' ';
    }




    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Field field)) return false;
        return symbol == field.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }
}
