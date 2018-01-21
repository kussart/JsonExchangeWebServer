package app.util;

import app.model.User;
import app.service.interfaces.TimeService;
import app.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@EnableScheduling
@Service
public class AutoAwayStatus {

    private final UserService userService;
    private final TimeService timeService;

    @Autowired
    public AutoAwayStatus(UserService userService, TimeService timeService) {
        this.userService = userService;
        this.timeService = timeService;
    }

    @Scheduled(fixedDelay = 1000)
    private void statusAutoChanging(){
        List<User> usersList = userService.findAllChanged();
        DateTimeFormatter formatter = timeService.getFormatter();
        LocalDateTime currentTime = timeService.getCurrentTime();
        for(User user: usersList){
            LocalDateTime userTimeInBase = LocalDateTime.parse(user.getTime(), formatter);
            if (userTimeInBase.isBefore(currentTime.minusSeconds(300))){
                user.setChanged(false);
                user.setStatus("Away");
                user.setTime(currentTime.format(formatter));
                userService.save(user);
            }
        }
    }

}
