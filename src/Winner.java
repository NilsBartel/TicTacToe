public class Winner {



    public static boolean thereIsWinner(Board board, int index, char currentSymbol) {

        int counter;

        Position position = new Position(index);
        int row = position.getRow();
        int column = position.getColumn();

        // checks the rows
        counter = 0;
        for (int columns = 0; columns < board.rows.size(); columns++) {

            //board.getRows().get(1).getFields().get(2).setSymbol('S');
            if (currentSymbol == board.getRows().get(row).getFields().get(columns).getSymbol()) {
                counter++;
            } else break;
        }
        if (counter == board.rows.size()) {
            return true;
        }


        // checks the columns
        counter = 0;
        for (int rows = 0; rows < board.rows.size(); rows++) {

            if (currentSymbol == board.getRows().get(rows).getFields().get(column).getSymbol()) {
                counter++;
            } else break;
        }
        if (counter == board.rows.size()) {
            return true;
        }




        // checks the diagonal top left to bottom right
        // 0/0, 1/1, 2/2
        counter = 0;
        for (int i = 0; i < board.rows.size(); i++) {
            if (currentSymbol == board.getRows().get(i).getFields().get(i).getSymbol()) {
                counter++;
            } else break;
        }
        if (counter == board.rows.size()) {
            return true;
        }






        // checks the diagonal top right to bottom left
        // 0/2, 1/1, 2/0     all = 2
        if(index == 3 || index == 5 || index == 7){

            counter = 0;
            int countDownTemp = 2;

            for (int i = 0; i < board.rows.size(); i++) {
                if (currentSymbol == board.getRows().get(i).getFields().get(countDownTemp).getSymbol()) {
                    counter++;
                } else break;
                countDownTemp = countDownTemp - 1;
            }
            if (counter == board.rows.size()) {
                return true;
            }
        }


        return false;
    }











}
