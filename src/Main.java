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

    static char spieler1 = 'o';
    static char spieler2 = 'x';
    static char leeresFeld = ' ';


    public static void main(String[] args) {

        System.out.println("Willkommen zu TicTacToe!");
        printField();


        for (int runden = 0; runden < 9; runden++) {
            int[] coordinaten;

            if (runden % 2 == 0) {
                int zug = spielerNachZugFragen(spieler1);

                while (!isValid(zug)) {
                    zug = spielerNachZugFragen(spieler1);
                }
                coordinaten = returnCoordinates(zug);
                spielFeld[coordinaten[0]][coordinaten[1]] = spieler1;


            } else {
                coordinaten = returnCoordinates(computerZug());
                spielFeld[coordinaten[0]][coordinaten[1]] = spieler2;
            }

            System.out.println();
            System.out.println();
            printField();

            if (runden > 3) {
                if (checkForWinner(coordinaten)) {
                    break;
                }
            }
        }

        System.out.println("Das Spiel ist zu ende!");

    }

    public static int spielerNachZugFragen(char spieler) {
        System.out.println("Spieler \"" + spieler + "\", geben sie eine Zahl von 1 bis 9 ein um ihr Zeichen zu setzen.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int computerZug() {
        Random rand = new Random();

        int n = rand.nextInt(8) + 1;
        int[] coordinaten = returnCoordinates(n);

        while(spielFeld[coordinaten[0]][coordinaten[1]] != leeresFeld) {
            n = rand.nextInt(8) + 1;
            coordinaten = returnCoordinates(n);
        }

        return n;
    }

    public static int[] returnCoordinates(int spielZug){

        return switch (spielZug) {
            case 1 -> new int[]{0, 0};

            case 2 -> new int[]{0, 2};

            case 3 -> new int[]{0, 4};

            case 4 -> new int[]{2, 0};

            case 5 -> new int[]{2, 2};

            case 6 -> new int[]{2, 4};

            case 7 -> new int[]{4, 0};

            case 8 -> new int[]{4, 2};

            case 9 -> new int[]{4, 4};

            default -> new int[]{-1, -1};
        };
    }

    public static boolean isValid(int spielZug) {

        if (spielZug < 1 || spielZug > 9) {
            System.out.println();
            System.out.println("Fehler, die Eingabe ist ungültig!");
            return false;
        }

        int[] coordinates = returnCoordinates(spielZug);

        if(spielFeld[coordinates[0]][coordinates[1]] != leeresFeld) {
            System.out.println();
            System.out.println("Dieses Feld ist bereits besetzt, bitte versuchen sie es nocheinmal!");
            return false;
        }

        return true;
    }

    public static void printField() {

        //for (int i = 0; i < spielFeld.length; i++)
        for (char[] chars : spielFeld) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static boolean checkForWinner(int[] coordinaten) {

        char temp;
        int counter;

        // checkt die reihen
        temp = spielFeld[coordinaten[0]][coordinaten[1]];
        counter = 0;
        for (int i = 0; i < spielFeld.length; i += 2) {

            if (temp == spielFeld[coordinaten[0]][i]) {
                counter++;
            } else break;
        }

        if (counter == 3) {
            printWhoWon(temp);
            return true;
        }


        // checkt die spalten
        temp = spielFeld[coordinaten[0]][coordinaten[1]];
        counter = 0;
        for (int i = 0; i < spielFeld.length; i += 2) {

            if (temp == spielFeld[i][coordinaten[1]]) {
                counter++;
            } else break;
        }

        if (counter == 3) {
            printWhoWon(temp);
            return true;
        }


        // checkt diagonale von links oben nach rechts unten
        // 0/0, 2/2, 4/4
        if(coordinaten[0] == coordinaten[1]) {
            temp = spielFeld[coordinaten[0]][coordinaten[1]];
            counter = 0;
            for (int i = 0; i < spielFeld.length; i += 2) {
                if (temp == spielFeld[i][i]) {
                    counter++;
                } else break;
            }
            if (counter == 3) {
                printWhoWon(temp);
                return true;
            }
        }

        // checkt diagonale von rechts oben nach links unten
        // 0/4, 2/2, 4/0
        if(coordinaten[0] + coordinaten[1] == 4) {
            temp = spielFeld[coordinaten[0]][coordinaten[1]];
            counter = 0;
            int countDownTemp = 4;

            System.out.println("ging in if");

            for (int i = 0; i < spielFeld.length; i += 2) {
                if (temp == leeresFeld) break;
                if (temp == spielFeld[i][countDownTemp]) {
                    counter++;
                } else break;
                countDownTemp = countDownTemp - 2;
            }
            if (counter == 3) {
                printWhoWon(temp);
                return true;
            }
        }


        return false;
    }

    public static void printWhoWon(char temp) {

        if (temp == spieler1) {
            System.out.println("spieler hat gewonnen");
        } else if (temp == spieler2) {
            System.out.println("computer hat gewonnen");
        }
    }

}