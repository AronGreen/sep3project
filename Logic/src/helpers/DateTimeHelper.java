package helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static boolean overlaps(
            LocalDateTime date1Start, LocalDateTime date1End,
            LocalDateTime date2Start, LocalDateTime date2End)
    {
        if (date1End.isBefore(date1Start) || date2End.isBefore(date2Start))
            throw new IllegalArgumentException("Date end has to be after date start.");

        return !(date1End.isBefore(date2Start) || date2End.isBefore(date1Start));
    }

    public static LocalDateTime fromString(String s) {
        String[] arr = s.split("-");

        return LocalDateTime.of(
                Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]),
                Integer.parseInt(arr[4])
        );
    }

    public static String toString(LocalDateTime t) {
        return String.join("-",
                t.getYear() + "",
                t.getMonthValue() + "",
                t.getDayOfMonth() + "",
                t.getHour() + "",
                t.getMinute() + "");
    }

    public static String getCurrentTime() {
        return toString(LocalDateTime.now());
    }
}
