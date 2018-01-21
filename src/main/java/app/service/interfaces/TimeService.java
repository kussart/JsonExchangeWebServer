package app.service.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TimeService {

    String getChangedTime();
    LocalDateTime getCurrentTime();
    DateTimeFormatter getFormatter();
}
