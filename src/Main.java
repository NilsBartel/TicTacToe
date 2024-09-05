import java.util.Random;
import java.util.Scanner;

public class Main {

    static char[][] spielFeld = new char[][]{
            {' ', '|', ' ', '|', ' '},
            {'-', '-', '-', '-', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '-', '-', '-', '-'},
            {' ', '|', ' ', '|', ' '},
    };

    static int[] felder= {0,0,0, 0,0,0, 0,0,0};
    static char spieler1 = 'o';
    static char spieler2 = 'x';
    static char leeresFeld = ' ';



    public static void main(String[] args) {

        System.out.println("Willkommen zu TictacToe!");
        printField(spielFeld);



        for(int runden = 0; runden < 9; runden++) {

            if(checkForWinner1()){
                break;
            }

            if(runden % 2 == 0){
                int zug = spielerZugAuswahl(spieler1);

                while(!isValid(zug)){
                    zug = spielerZugAuswahl(spieler1);
                }
                zeichenSetzen(zug, spieler1);


            } else zeichenSetzen(computerZug(), spieler2);

            //printArrayTest();
            System.out.println();
            System.out.println();
            printField(spielFeld);
        }
        System.out.println("Das Spiel ist zu ende!");

    }

    public static void printArrayTest(){
        for(int i = 0; i < felder.length; i++){
            //System.out.println();
            System.out.print(felder[i]+", ");
        }
    }

    public static boolean checkForWinner(){
        //0,2,4
        if(felder[0] == felder[1] && felder[1] == felder[2] && felder[0] != 0){
            if(spielFeld[0][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        } else if (felder[3] == felder[4] && felder[4] == felder[5] && felder[3] != 0) {
            if(spielFeld[0][3] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        } else if (felder[6] == felder[7] && felder[7] == felder[8] && felder[6] != 0) {
            if(spielFeld[0][6] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        }

        if(felder[0] == felder[3] && felder[3] == felder[6] && felder[0] != 0){
            if(spielFeld[0][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        } else if (felder[1] == felder[4] && felder[4] == felder[7] && felder[1] != 0) {
            if(spielFeld[1][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        } else if (felder[2] == felder[5] && felder[5] == felder[8] && felder[2] != 0) {
            if(spielFeld[2][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        }

        if(felder[0] == felder[4] && felder[4] == felder[8] && felder[0] != 0){
            if(spielFeld[0][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        } else if (felder[2] == felder[4] && felder[4] == felder[6] && felder[2] != 0) {
            if(spielFeld[2][0] == spieler1){
                System.out.println("spieler 1 hat gewonnen!");
                return true;
            }else System.out.println("der computer hat gewonnen!");
            return true;
        }
        return false;

    }

    public static void printWhoWon(char temp){

        if(temp == spieler1) {
            System.out.println("spieler hat gewonnen");
        } else if(temp == spieler2){
            System.out.println("computer hat gewonnen");
        }
    }

    public static boolean checkForWinner1(){

        char temp;
//        char temp2;
//
//        for(int i = 0; i < spielFeld.length; i+=2){
//            temp = spielFeld[i][0];
//            temp2 = spielFeld[0][i];
//            int counterRow = 0;
//            int counterCol = 0;
//
//            for(int j = 0; j < spielFeld[i].length; j+=2){
//                if(temp == leeresFeld && temp2 == leeresFeld) break;
//                if(temp == spielFeld[i][j]){
//                    counterRow++;
//                }
//                if(temp2 == spielFeld[j][i]){
//                    counterCol++;
//                } else break;
//            }
//
//            if(counterRow == 3){
//                printWhoWon(temp);
//                return true;
//            } else if(counterCol == 3){
//                printWhoWon(temp2);
//                return true;
//            }
//        }



         // checkt die reihen
        for(int i = 0; i < spielFeld.length; i+=2){
            temp = spielFeld[i][0];
            int counter = 0;

            for(int j = 0; j < spielFeld[i].length; j+=2){
                if(temp == leeresFeld) break;
                if(temp == spielFeld[i][j]){
                    counter++;
                } else break;
            }

            if(counter == 3){
                printWhoWon(temp);
                return true;
            }
        }

        // checkt die spalten
        for(int i = 0; i < spielFeld.length; i+=2) {
            temp = spielFeld[0][i];
            int counter = 0;

            for (int j = 0; j < spielFeld[i].length; j += 2) {
                if (temp == leeresFeld) break;
                if (temp == spielFeld[j][i]) {
                    counter++;
                } else break;
            }

            if(counter == 3){
                printWhoWon(temp);
                return true;
            }
        }

        // checkt diagonale von links oben nach rechts unten
        temp = spielFeld[0][0];
        int counter = 0;
        for(int i = 0; i < spielFeld.length; i+=2) {
            if (temp == leeresFeld) break;
            if (temp == spielFeld[i][i]) {
                counter++;
            } else break;
        }
        if(counter == 3){
            printWhoWon(temp);
            return true;
        }

        // checkt diagonale von rechts oben nach links unten
        // 0/4, 2/2, 4/0
        temp = spielFeld[0][4];
        int countDownTemp = 4;
        counter = 0;
        for(int i = 0; i < spielFeld.length; i+=2) {
            if (temp == leeresFeld) break;
            if (temp == spielFeld[i][countDownTemp]) {
                counter++;
            } else break;
            countDownTemp = countDownTemp - 2;
        }
        if(counter == 3){
            printWhoWon(temp);
            return true;
        }


        return false;
    }


    public static boolean isValid(int auswahl){

        if(auswahl < 1 || auswahl > 9){
            System.out.println();
            System.out.println("Fehler, die Eingabe ist ungültig!");
            return false;
        }
        if(felder[auswahl-1] != 0){
            System.out.println();
            System.out.println("Dieses Feld ist bereits besetzt, bitte versuchen sie es nocheinmal!");
            return false;
        }

        return true;
    }

    public static int spielerZugAuswahl(char spieler){
        System.out.println("Spieler \"" +spieler+ "\", geben sie eine Zahl von 1 bis 9 ein um ihr Zeichen zu setzen.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void printField(char[][] spielFeld) {

        for (int i = 0; i < spielFeld.length; i++) {
            for (int j = 0; j < spielFeld[i].length; j++) {
                System.out.print(spielFeld[i][j]);
            }
            System.out.println();
        }
    }

    public static void zeichenSetzen(int feld, char player){

        if(player == spieler1){
            felder[feld-1] = 1;
        } else  felder[feld-1] = 2;


        switch (feld) {
            case 1:
                spielFeld[0][0] = player;
                break;
            case 2:
                spielFeld[0][2] = player;
                break;
            case 3:
                spielFeld[0][4] = player;
                break;
            case 4:
                spielFeld[2][0] = player;
                break;
            case 5:
                spielFeld[2][2] = player;
                break;
            case 6:
                spielFeld[2][4] = player;
                break;
            case 7:
                spielFeld[4][0] = player;
                break;
            case 8:
                spielFeld[4][2] = player;
                break;
            case 9:
                spielFeld[4][4] = player;
                break;

        }

    }

    public static int computerZug(){
        Random rand = new Random();
        int n = 0;
        n = rand.nextInt(8)+1;

        //System.out.println(n);
        while(felder[n-1] != 0){
            n = rand.nextInt(8)+1;
            //System.out.println(n);
        }

        return n;
    }
}