public class PrintService {


    public static void printSecondsElapsed(Long startTime, Long endTime) {
        System.out.println(TimeUtility.convertToSeconds(startTime, endTime) + " seconds");
    }

    public static void printDate(Long milliseconds) {
        TimeUtility.printDate(milliseconds);
    }

    public static void printBoardNr(int counter) {
        System.out.println();
        System.out.println("Board: " + counter);
    }






}
