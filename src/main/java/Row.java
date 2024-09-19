import java.util.ArrayList;
import java.util.List;

public class Row {
    List<Field> fields = new ArrayList<>();

    public Row() {
        for(int column = 0; column<3; column++){
            fields.add(new Field());
        }
    }


    public void print(){

        List<String> symbols = new ArrayList<>();
        for(Field field : fields){
            symbols.add(String.valueOf(field.getSymbol()));
        }

        String line = String.join(" | ", symbols);
        System.out.println(line);
    }

    public List<Field> getFields() {
        return fields;
    }

    public Field getField(int column){
        return fields.get(column);
    }

    public void setSymbol(int column, char symbol) {
        fields.get(column).setSymbol(symbol);
    }

    public char getSymbol(int column){
        return fields.get(column).getSymbol();
    }


}
