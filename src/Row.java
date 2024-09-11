import java.util.ArrayList;

public class Row {
    ArrayList<Field> fields = new ArrayList<>();

    public Row(int row) {
        for(int column = 0; column<3; column++){
            Position position = new Position(row, column);
            fields.add(new Field(position));
        }
    }


    public void print(){

        ArrayList<String> symbols = new ArrayList<>();
        for(Field field : fields){
            symbols.add(String.valueOf(field.getSymbol()));
        }

        String line = String.join(" | ", symbols);
        System.out.println(line);
    }

    public ArrayList<Field> getFields() {
        return fields;
    }


}
