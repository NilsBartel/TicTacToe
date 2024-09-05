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

    static int[] felder = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    static char spieler1 = 'o';
    static char spieler2 = 'x';
    static char leeresFeld = ' ';


    public static void main(String[] args) {

        System.out.println("Willkommen zu TictacToe!");
        printField();


        for (int runden = 0; runden < 9; runden++) {
            int[] coordinaten;

            if (runden % 2 == 0) {
                int zug = spielerZugAuswahl(spieler1);

                while (!isValid(zug)) {
                    zug = spielerZugAuswahl(spieler1);
                }
                coordinaten = zeichenSetzen(zug, spieler1);


            } else coordinaten = zeichenSetzen(computerZug(), spieler2);

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

    public static int spielerZugAuswahl(char spieler) {
        System.out.println("Spieler \"" + spieler + "\", geben sie eine Zahl von 1 bis 9 ein um ihr Zeichen zu setzen.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int computerZug() {
        Random rand = new Random();

        int n = rand.nextInt(8) + 1;

        //System.out.println(n);
        while (felder[n - 1] != 0) {
            n = rand.nextInt(8) + 1;
            //System.out.println(n);
        }

        return n;
    }

    public static int[] zeichenSetzen(int feld, char player) {

        if (player == spieler1) {
            felder[feld - 1] = 1;
        } else felder[feld - 1] = 2;


        return switch (feld) {
            case 1 -> {
                spielFeld[0][0] = player;
                yield new int[]{0, 0};
            }
            case 2 -> {
                spielFeld[0][2] = player;
                yield new int[]{0, 2};
            }
            case 3 -> {
                spielFeld[0][4] = player;
                yield new int[]{0, 4};
            }
            case 4 -> {
                spielFeld[2][0] = player;
                yield new int[]{2, 0};
            }
            case 5 -> {
                spielFeld[2][2] = player;
                yield new int[]{2, 2};
            }
            case 6 -> {
                spielFeld[2][4] = player;
                yield new int[]{2, 4};
            }
            case 7 -> {
                spielFeld[4][0] = player;
                yield new int[]{4, 0};
            }
            case 8 -> {
                spielFeld[4][2] = player;
                yield new int[]{4, 2};
            }
            case 9 -> {
                spielFeld[4][4] = player;
                yield new int[]{4, 4};
            }
            default -> null;
        };
    }

    public static boolean isValid(int auswahl) {

        if (auswahl < 1 || auswahl > 9) {
            System.out.println();
            System.out.println("Fehler, die Eingabe ist ungültig!");
            return false;
        }
        if (felder[auswahl - 1] != 0) {
            System.out.println();
            System.out.println("Dieses Feld ist bereits besetzt, bitte versuchen sie es nocheinmal!");
            return false;
        }

        return true;
    }

    public static void printField() {

        for (int i = 0; i < spielFeld.length; i++) {
            for (int j = 0; j < spielFeld[i].length; j++) {
                System.out.print(spielFeld[i][j]);
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
        for (int i = 0; i < 5; i += 2) {

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
        for (int i = 0; i < 5; i += 2) {

            if (temp == spielFeld[i][coordinaten[1]]) {
                counter++;
            } else break;
        }

        if (counter == 3) {
            printWhoWon(temp);
            return true;
        }


        // checkt diagonale von links oben nach rechts unten
        temp = spielFeld[0][0];
        counter = 0;
        for (int i = 0; i < spielFeld.length; i += 2) {
            if (temp == leeresFeld) break;
            if (temp == spielFeld[i][i]) {
                counter++;
            } else break;
        }
        if (counter == 3) {
            printWhoWon(temp);
            return true;
        }

        // checkt diagonale von rechts oben nach links unten
        // 0/4, 2/2, 4/0
        temp = spielFeld[0][4];
        int countDownTemp = 4;
        counter = 0;
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