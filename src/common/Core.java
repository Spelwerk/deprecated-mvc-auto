package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class Core {
    public String getFilePath(String directory, String file) {
        File dir = new File(System.getProperty("user.dir") + File.separator + directory);
        // Create directory if the directory is not present
        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdirs();
        }
        return new File(dir, file).getAbsolutePath();
    }

    public String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        // Format the date according to something resembling of ISO standard.
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'hh_mm_ss");
        return formater.format(calendar.getTime());
    }

    public String timeTodaySetHour(int hour) {
        return ZonedDateTime.now().withHour(hour).withMinute(0).withSecond(0).format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
    }

    public String timeTodayRandom(int minHour, int maxHour) {
        int randomHour = ThreadLocalRandom.current().nextInt(minHour, maxHour + 1);

        return ZonedDateTime.now().plusHours(randomHour).withMinute(0).withSecond(0).format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
    }
}
