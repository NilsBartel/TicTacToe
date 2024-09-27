import java.util.concurrent.TimeUnit;

public class Time {

    private long startTime;
    private long endTime;



    public void setStartTime(){
        startTime = System.nanoTime();
    }

    public void setEndTime(){
        endTime = System.nanoTime();
    }

    public long getElapsedTimeInSeconds(){
        return TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);

    }


}
