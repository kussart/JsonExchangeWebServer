package app.service.interfaces;

import app.model.User;
import app.util.ChangedStatusResponse;

import java.util.List;

public interface UserService {

    void save(User user);
    User findById(Long id);
    ChangedStatusResponse changeStatus(Long id, String changedStatus);
    List<User> findAllChanged();

}
