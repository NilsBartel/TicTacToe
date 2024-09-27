import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class TimeUtility {
    private TimeUtility() {
    }


    public static long convertToSeconds(List<Long> timeList) {
        return TimeUnit.MILLISECONDS.toSeconds(timeList.getLast() - timeList.getFirst());
    }

    public static void printDate(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
        Date resultdate = new Date(time);
        System.out.println(sdf.format(resultdate));
    }

}
