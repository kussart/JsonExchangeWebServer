package app.service.impl;

import app.service.interfaces.TimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class TimeServiceImpl implements TimeService {


    @Override
    public String getChangedTime(){
        return LocalDateTime.now().format(getFormatter());
    }

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss").withLocale(new Locale("ru"));
    }
}
