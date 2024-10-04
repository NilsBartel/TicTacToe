public class PrintService {
    private static PrintService instance;

    private PrintService() {
    }
    public static PrintService getInstance() {
        if (instance == null) {
            instance = new PrintService();
        }
        return instance;
    }

    public void printSecondsElapsed(Long startTime, Long endTime) {
        System.out.println(TimeUtility.convertToSeconds(startTime, endTime) + " seconds");
    }

    public void printDate(Long milliseconds) {
        TimeUtility.printDate(milliseconds);
    }

    public void printBoardNr(int counter) {
        System.out.println();
        System.out.println("Board: " + counter);
    }

}
