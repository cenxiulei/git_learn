package MVC.learn.demo.service;

import MVC.learn.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUserById(long id);
    User creatUser(User user);
    void deleteUser(long id);
}
