import java.util.Random;
import java.util.Scanner;

public class Main {

    static char[][] spielFeld = new char[][]{
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
    };

    static char spieler1 = 'o';
    static char spieler2 = 'x';
    static char leeresFeld = ' ';
    static int fieldSize = 3;
    static int computerScore = 0;
    static int playerScore = 0;



    public static void main(String[] args) {

        System.out.println("Willkommen zu TicTacToe!");


        do {
            gameLoop();
        } while(askPlayAgain());


        displayGameEndMessage();
    }

    public static int computerMove(){

        // checks if computer can win with the next move
        for (int i = 1; i < fieldSize*fieldSize+1; i++) {
            int[] coordinates = returnCoordinates(i);

            if(spielFeld[coordinates[0]][coordinates[1]] != leeresFeld) continue;


            spielFeld[coordinates[0]][coordinates[1]] = spieler2;
            if (checkForWinner(coordinates)){
                System.out.println("not random move");
                spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
                return i;
            }
            spielFeld[coordinates[0]][coordinates[1]] = leeresFeld;
        }


        // checks if computer can lose with the next move

        Random rand = new Random();
        int n;
        int[] coordinates;
        do{
            n = rand.nextInt(8) + 1;
            coordinates = returnCoordinates(n);
        }   while (spielFeld[coordinates[0]][coordinates[1]] != leeresFeld);

        System.out.println("random move");
        return n;

    }

    public static void gameLoop(){

        printField();

        int spielZugCounter = 0;

        while (true) {

            int[] coordinaten;

            if (spielZugCounter % 2 == 0) {
                int zug;

                do{
                    zug = spielerNachZugFragen(spieler1);
                } while (!isValid(zug));

                coordinaten = returnCoordinates(zug);
                spielFeld[coordinaten[0]][coordinaten[1]] = spieler1;


            } else {
                coordinaten = returnCoordinates(computerMove());
                spielFeld[coordinaten[0]][coordinaten[1]] = spieler2;
            }

            System.out.println();
            printField();

            if (spielZugCounter > 3) {
                if (checkForWinner(coordinaten)) {
                    break;
                }
            }
            spielZugCounter++;

            if (spielZugCounter > fieldSize*fieldSize-1) {
                System.out.println("Es ist gleichstand!");
                break;
            }
        }

    }

    public static int spielerNachZugFragen(char spieler) {
        System.out.println("Spieler \"" + spieler + "\", geben sie eine Zahl von 1 bis 9 ein um ihr Zeichen zu setzen.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int computerZug() {
        Random rand = new Random();

        int n;
        int[] coordinaten;

        do{
            n = rand.nextInt(8) + 1;
            coordinaten = returnCoordinates(n);
        }   while (spielFeld[coordinaten[0]][coordinaten[1]] != leeresFeld);

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

        if (spielZug < 1 || spielZug > fieldSize*fieldSize) {
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

        if (counter == fieldSize) {
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

        if (counter == fieldSize) {
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
            if (counter == fieldSize) {
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

            for (int i = 0; i < spielFeld.length; i += 2) {
                if (temp == leeresFeld) break;
                if (temp == spielFeld[i][countDownTemp]) {
                    counter++;
                } else break;
                countDownTemp = countDownTemp - 2;
            }
            if (counter == fieldSize) {
                printWhoWon(temp);
                return true;
            }
        }


        return false;
    }

    public static void printWhoWon(char temp) {

        if (temp == spieler1) {
            playerScore++;
            System.out.println("You won the round! Wohoo!");
        } else if (temp == spieler2) {
            computerScore++;
            System.out.println("The computer won the round! Better luck next time!");
        }
    }

    public static boolean askPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        String response;

        System.out.println();
        System.out.println("The score is:");
        System.out.println("Player Score: " + playerScore + "      " + "Computer Score: " + computerScore);

        System.out.println();
        System.out.println("Do you want to play again? (Type y/n)");
        response = scanner.nextLine();

        while(! (response.equals("y") || response.equals("n"))){
            System.out.println("This is not a valid input! Please try again.");
            response = scanner.nextLine();
        }

        if(response.equals("y")){
            resetSpielFeld();
            return true;
        } else {
            return false;
        }

    }

    public static void resetSpielFeld(){

        for(int i = 0; i < spielFeld.length; i+=2){
            for(int j = 0; j < spielFeld[0].length; j+=2){
                spielFeld[i][j] = leeresFeld;
            }
        }
    }

    public static void displayGameEndMessage() {

        System.out.println();
        System.out.println("Das Spiel ist zu ende!");
        System.out.println("The final score is:");
        System.out.println("Player Score: " + playerScore + "      " + "Computer Score: " + computerScore);
        System.out.println();
        if(playerScore > computerScore) {
            System.out.println("You won the game! Congrats!!!");
        } else if (computerScore > playerScore) {
            System.out.println("You lost the game!");
        } else {
            System.out.println("The end score is even! No one won!");
        }
    }
}