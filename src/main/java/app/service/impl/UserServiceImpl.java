package app.service.impl;

import app.model.User;
import app.repository.UserRepository;
import app.service.interfaces.TimeService;
import app.service.interfaces.UserService;
import app.util.ChangedStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TimeService timeService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TimeService timeService) {
        this.userRepository = userRepository;
        this.timeService = timeService;
    }

    @Override
    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAllChanged() {
        return userRepository.findAllByChangedIsTrue();
    }

    @Override
    public ChangedStatusResponse changeStatus(Long id, String changedStatus) {
        User user = userRepository.findOne(id);
        String oldStatus = user.getStatus();
        user.setStatus(changedStatus);
        user.setTime(timeService.getChangedTime());
        user.setChanged(true);
        userRepository.save(user);
        ChangedStatusResponse userDetailsResponse = new ChangedStatusResponse();
        userDetailsResponse.setId(id);
        userDetailsResponse.setCurrentStatus(changedStatus);
        userDetailsResponse.setOldStatus(oldStatus);
        return userDetailsResponse;
    }

}
