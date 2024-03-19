package util;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    
    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);

        return formattedTime;
    }
}
