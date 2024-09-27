import java.util.concurrent.TimeUnit;

public class Time {

    private long startTime;
    private long endTime;



    public void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    public void setEndTime(){
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTimeInSeconds(){
        return TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
