import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class TimeUtil {
    private TimeUtil() {
    }


    public static long convertToSeconds(Long startTime, Long endTime) {
        return TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
    }

    public static void printDate(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
        Date resultdate = new Date(time);
        System.out.println(sdf.format(resultdate));
    }

}
